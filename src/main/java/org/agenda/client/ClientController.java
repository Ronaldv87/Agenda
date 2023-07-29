package org.agenda.client;

import org.agenda.core.mapper.AppointmentMapper;
import org.agenda.core.service.AppointmentService;
import org.agenda.model.Appointment;
import org.agenda.repo.AppointmentCacheRepository;

import java.util.List;

public class ClientController {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentService appointmentService;

    public ClientController() {
        // Necessary evil right here.. solution would be to use the Factory Pattern.
        final AppointmentCacheRepository appointmentCacheRepository = new AppointmentCacheRepository();
        this.appointmentService = new AppointmentService(appointmentCacheRepository);
        this.appointmentMapper = new AppointmentMapper(this.appointmentService);
    }

    public void createAppointment(String dateTime) {
        Appointment appointment = appointmentMapper.convertStringToAppointment(dateTime);
        Appointment createdAppointment = appointmentService.create(appointment);
        printAppointment(appointmentMapper.convertAppointmentToString(createdAppointment));
    }

    public void openAppointment(String appointmentId) {
        Appointment appointment = appointmentService.read(appointmentId);
        printAppointment(appointmentMapper.convertAppointmentToString(appointment));
    }

    public void updateAppointment(String input) {
        Appointment appointment = appointmentMapper.convertStringToAppointmentWithId(input);
        Appointment updatedAppointment = appointmentService.update(appointment);
        printAppointment(appointmentMapper.convertAppointmentToString(updatedAppointment));
    }

    public void deleteAppointment(String input) {
        Appointment appointment = appointmentService.read(input);
        boolean isDeleted = appointmentService.delete(appointment);
        if(isDeleted) {
            System.out.println("The appointment has been deleted");
        } else {
            System.out.println("The appointment has not been deleted");
        }
    }

    public void printAppointment(String appointmentText) {
        String[] parts = appointmentText.split("/");
        System.out.println("Appointment id: " + parts[0]);
        System.out.println("Start time: " + parts[1]);
        System.out.println("End time: " + parts[2]);
    }

    public void printAllAppointments() {
        List<Appointment> appointmentList = appointmentService.getAllAppointments();
        if(appointmentList.isEmpty()) {
            System.out.println("you have no appointments planned.");
        } else {
            List<String> convertedAppointmentList = appointmentMapper.convertListOfAppointmentsToString(appointmentList);
            for(String appointmentToPrint : convertedAppointmentList) {
                printAppointment(appointmentToPrint);
                System.out.println("---------------------");
            }
        }
    }

    public String concatenateInputWithId(String appointmentId, String dates) {
        return appointmentId + "/" + dates;
    }

    public String concatenateInput(String startDateTime, String endDateTime) {
        return startDateTime + "/" + endDateTime;
    }
}
