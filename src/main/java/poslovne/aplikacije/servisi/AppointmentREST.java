package poslovne.aplikacije.servisi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import poslovne.aplikacije.appointments.Appointment;
import poslovne.aplikacije.appointments.AppointmentStatus;
import poslovne.aplikacije.messaging.AppointmentRequestDTO;
import poslovne.aplikacije.repository.AppointmentRepository;

@RestController
@RequestMapping("/appointments")
public class AppointmentREST {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping
    public Appointment create(@RequestBody AppointmentRequestDTO dto) {
        return appointmentService.createRequest(dto);
    }

    @GetMapping
    public List<Appointment> list(@RequestParam(value = "status", required = false) AppointmentStatus status) {
        if (status == null) return appointmentRepository.findAll();
        return appointmentRepository.findAll().stream().filter(a -> a.getStatus() == status).collect(java.util.stream.Collectors.toList());
    }
}
