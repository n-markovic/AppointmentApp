package poslovne.aplikacije.exceptions;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException(Long id) {
        super("Ne postoji zakazivanje sa id-jem: " + id);
    }
}
