package poslovne.aplikacije.messaging;

import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    public void receiveNotification(NotificationMessage msg) {
        if (msg == null) return;

        if (msg.getStatus() == null || msg.getStatus().isEmpty()) {
            System.out.println("[NOTIFICATION] Novi zahtev za rezervaciju: id=" + msg.getAppointmentId()
                    + " pacijent=" + msg.getPatientFirstName() + " " + msg.getPatientLastName()
                    + " doctorId=" + msg.getDoctorId()
                    + " datum=" + msg.getAppointmentDate() + " vreme=" + msg.getAppointmentTime());
        } else {
            System.out.println("[NOTIFICATION] Rezultat zakazivanja: id=" + msg.getAppointmentId()
                    + " status=" + msg.getStatus()
                    + " pacijent=" + msg.getPatientFirstName() + " " + msg.getPatientLastName()
                    + " doctorId=" + msg.getDoctorId()
                    + " datum=" + msg.getAppointmentDate() + " vreme=" + msg.getAppointmentTime());
        }
    }
}
