package poslovne.aplikacije.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import poslovne.aplikacije.appointments.AppointmentStatus;
import poslovne.aplikacije.appointments.Doctor;
import poslovne.aplikacije.repository.AppointmentRepository;

@Component
public class AppointmentEventListener {

    @Autowired
    private AppointmentRepository appointmentRepository;


    public void receiveMessage(AppointmentRequestEvent event) {
        if (event == null || event.getAppointmentId() == null) return;

        appointmentRepository.findById(event.getAppointmentId()).ifPresent(appointment -> {
            // simple conflict check: exact same date + time for the same doctor
            java.time.LocalDate date = java.time.LocalDate.parse(event.getAppointmentDate());
            java.time.LocalTime time = java.time.LocalTime.parse(event.getAppointmentTime());
            Doctor doctor = appointment.getDoctor();
            boolean conflict = appointmentRepository.existsByDoctorAndAppointmentDateAndAppointmentTimeAndStatus(doctor, date, time, AppointmentStatus.CONFIRMED);
            if (!conflict) {
                appointment.setStatus(AppointmentStatus.CONFIRMED);
            } else {
                appointment.setStatus(AppointmentStatus.REJECTED);
            }
            appointmentRepository.save(appointment);
        });
    }
}
