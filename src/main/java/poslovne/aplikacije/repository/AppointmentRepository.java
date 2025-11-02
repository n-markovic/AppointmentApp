package poslovne.aplikacije.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poslovne.aplikacije.appointments.Appointment;
import poslovne.aplikacije.appointments.AppointmentStatus;
import poslovne.aplikacije.appointments.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	boolean existsByDoctorAndAppointmentDateAndAppointmentTimeAndStatus(Doctor doctor, LocalDate appointmentDate, LocalTime appointmentTime, AppointmentStatus status);

	public List<Appointment> findAll();
}
