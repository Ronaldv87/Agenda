package org.agenda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {
    @Test
    void getAppointment() {
        Agenda agenda = new Agenda();
        var appointment = agenda.getAppointment();

        assertInstanceOf(Appointment.class, appointment);
        assertNotNull(appointment.getDateTime());
    }
}