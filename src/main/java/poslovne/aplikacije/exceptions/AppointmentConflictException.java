package poslovne.aplikacije.exceptions;

public class AppointmentConflictException extends RuntimeException {

    public AppointmentConflictException(Long doctorId, String date, String time) {
        super("Lekar sa id-jem " + doctorId + " je zauzet u terminu " + date + " " + time);
    }

    public AppointmentConflictException(String message) {
        super(message);
    }
}
