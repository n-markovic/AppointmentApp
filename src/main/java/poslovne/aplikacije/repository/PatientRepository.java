package poslovne.aplikacije.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poslovne.aplikacije.appointments.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
