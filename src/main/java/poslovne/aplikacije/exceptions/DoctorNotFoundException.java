package poslovne.aplikacije.exceptions;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(Long id) {
        super("Ne postoji lekar sa id-jem: " + id);
    }
}
