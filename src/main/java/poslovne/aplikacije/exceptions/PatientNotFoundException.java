package poslovne.aplikacije.exceptions;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(Long id) {
        super("Ne postoji pacijent sa id-jem: " + id);
    }
}
