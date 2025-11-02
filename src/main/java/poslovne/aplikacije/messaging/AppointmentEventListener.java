package poslovne.aplikacije.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import poslovne.aplikacije.RabbitMQConfigurator;
import poslovne.aplikacije.appointments.AppointmentStatus;
import poslovne.aplikacije.appointments.Doctor;
import poslovne.aplikacije.repository.AppointmentRepository;

@Component
public class AppointmentEventListener {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Method name wired from RabbitMQConfigurator
    public void handleAppointmentEvent(AppointmentRequestEvent event) {
        if (event == null || event.getAppointmentId() == null) return;

        appointmentRepository.findById(event.getAppointmentId()).ifPresent(appointment -> {
            // simple conflict check: exact same date + time for the same doctor
            java.time.LocalDate date = poslovne.aplikacije.util.DateTimeUtils.parseDate(event.getAppointmentDate());
            java.time.LocalTime time = poslovne.aplikacije.util.DateTimeUtils.parseTime(event.getAppointmentTime());
            Doctor doctor = appointment.getDoctor();
            boolean conflict = appointmentRepository.existsByDoctorAndAppointmentDateAndAppointmentTimeAndStatus(doctor, date, time, AppointmentStatus.CONFIRMED);
            if (!conflict) {
                appointment.setStatus(AppointmentStatus.CONFIRMED);
            } else {
                appointment.setStatus(AppointmentStatus.REJECTED);
            }
            appointmentRepository.save(appointment);

            // publish a result notification so UIs/listeners can show it
            poslovne.aplikacije.messaging.NotificationMessage n = new poslovne.aplikacije.messaging.NotificationMessage(
                    appointment.getId(),
                    doctor.getId(),
                    appointment.getPatient().getFirstName(),
                    appointment.getPatient().getLastName(),
                    appointment.getAppointmentDate().toString(),
                    appointment.getAppointmentTime().toString(),
                    appointment.getStatus().getDisplayName());

            rabbitTemplate.convertAndSend(RabbitMQConfigurator.NOTIFICATIONS_TOPIC_EXCHANGE_NAME, "appointments.notifications.result", n);
        });
    }
}
