package poslovne.aplikacije.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
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

/*
@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod, Long> {
	public List<Proizvod> findAll();
	
	public List<Proizvod> findByKat(KategorijeProizvoda kat);

	public Proizvod save(Proizvod noviProizvod);

	public Optional<Proizvod> findById(long id);
	
	public void deleteById(Long id);
}*/