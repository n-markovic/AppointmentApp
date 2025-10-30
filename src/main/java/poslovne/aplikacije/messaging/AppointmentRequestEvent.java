package poslovne.aplikacije.messaging;

import java.io.Serializable;

public class AppointmentRequestEvent implements Serializable {

    private Long appointmentId;
    private Long doctorId;
    private String appointmentDate; // ISO date string
    private String appointmentTime; // ISO time string

    public AppointmentRequestEvent() {}

    public AppointmentRequestEvent(Long appointmentId, Long doctorId, String appointmentDate, String appointmentTime) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
