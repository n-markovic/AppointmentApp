package poslovne.aplikacije.servisi;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import poslovne.aplikacije.RabbitMQConfigurator;
import poslovne.aplikacije.appointments.Appointment;
import poslovne.aplikacije.appointments.Doctor;
import poslovne.aplikacije.appointments.Patient;
import poslovne.aplikacije.messaging.AppointmentRequestDTO;
import poslovne.aplikacije.messaging.AppointmentRequestEvent;
import poslovne.aplikacije.repository.AppointmentRepository;
import poslovne.aplikacije.repository.DoctorRepository;
import poslovne.aplikacije.repository.PatientRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public Appointment createRequest(AppointmentRequestDTO dto) {
        Patient patient = patientRepository.findByFirstNameAndLastName(dto.getPatientFirstName(), dto.getPatientLastName())
                .orElseGet(() -> {
                    Patient p = new Patient(dto.getPatientFirstName(), dto.getPatientLastName());
                    return patientRepository.save(p);
                });

        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new IllegalArgumentException("Doctor not found"));

        java.time.LocalDate date;
        java.time.LocalTime time;
        try {
            date = poslovne.aplikacije.util.DateTimeUtils.parseDate(dto.getAppointmentDate());
            time = poslovne.aplikacije.util.DateTimeUtils.parseTime(dto.getAppointmentTime());
        } catch (java.time.format.DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid appointmentDate or appointmentTime format. Use yyyy-MM-dd (e.g. 2025-11-03) and HH:mm (e.g. 14:30)");
        }

        Appointment appointment = new Appointment(doctor, patient, date, time);
        appointment = appointmentRepository.save(appointment);

    AppointmentRequestEvent evt = new AppointmentRequestEvent(appointment.getId(), doctor.getId(), dto.getAppointmentDate(), dto.getAppointmentTime());
        rabbitTemplate.convertAndSend(RabbitMQConfigurator.APPOINTMENTS_TOPIC_EXCHANGE_NAME, "appointments.events.request", evt);

    poslovne.aplikacije.messaging.NotificationMessage n = new poslovne.aplikacije.messaging.NotificationMessage(
        appointment.getId(), doctor.getId(), patient.getFirstName(), patient.getLastName(), dto.getAppointmentDate(), dto.getAppointmentTime(), null);
    rabbitTemplate.convertAndSend(RabbitMQConfigurator.NOTIFICATIONS_TOPIC_EXCHANGE_NAME, "appointments.notifications.request", n);

        return appointment;
    }
}
