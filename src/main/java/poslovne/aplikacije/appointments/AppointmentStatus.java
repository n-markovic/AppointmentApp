package poslovne.aplikacije.appointments;

public enum AppointmentStatus {
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    REJECTED("REJECTED");

    private final String displayName;

    AppointmentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
