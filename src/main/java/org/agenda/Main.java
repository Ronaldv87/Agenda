package org.agenda;

import org.agenda.client.Client;
import org.agenda.core.AppointmentService;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
