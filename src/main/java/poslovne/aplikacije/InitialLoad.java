package poslovne.aplikacije;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import poslovne.aplikacije.appointments.Doctor;
import poslovne.aplikacije.appointments.Patient;
import poslovne.aplikacije.repository.DoctorRepository;
import poslovne.aplikacije.repository.PatientRepository;

@Configuration
public class InitialLoad {
	
	private static final Logger log = LoggerFactory.getLogger(InitialLoad.class);
	
	@Bean
	public CommandLineRunner loadData(DoctorRepository doctorRepository, PatientRepository patientRepository) {
        return (args) -> {
            if (doctorRepository.count() == 0) {
                doctorRepository.save(new Doctor("Dr. Ana Petrovic", "General practice"));
                doctorRepository.save(new Doctor("Dr. Ivan Jovic", "Cardiology"));
            }

            if (patientRepository.count() == 0) {
                patientRepository.save(new Patient("Marko", "Markovic"));
                patientRepository.save(new Patient("Jelena", "Jankovic"));
            }

            log.info("All data loaded.");
        };
    }
}
