package poslovne.aplikacije.messaging;

import java.io.Serializable;

public class NotificationMessage implements Serializable {

    private Long appointmentId;
    private Long doctorId;
    private String patientFirstName;
    private String patientLastName;
    private String appointmentDate;
    private String appointmentTime;
    private String status;

    public NotificationMessage() {}

    public NotificationMessage(Long appointmentId, Long doctorId, String patientFirstName, String patientLastName, String appointmentDate, String appointmentTime, String status) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public String getPatientFirstName() { return patientFirstName; }
    public void setPatientFirstName(String patientFirstName) { this.patientFirstName = patientFirstName; }

    public String getPatientLastName() { return patientLastName; }
    public void setPatientLastName(String patientLastName) { this.patientLastName = patientLastName; }

    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
