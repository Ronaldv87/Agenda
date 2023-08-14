package org.agenda.client;

import java.util.Scanner;

public class ConsoleClient {

    private final ClientController clientController;

    public ConsoleClient() {
        this.clientController = new ClientController();
    }

    public void start() {
        menu();
    }

    public void menu() {
        System.out.println("What would you like to do?");
        System.out.println("1) Display all appointments");
        System.out.println("2) Create an appointment");
        System.out.println("3) Open an appointment");
        System.out.println("4) Update an appointment");
        System.out.println("5) Delete an appointment");
        System.out.println("6) Exit");
        menuSelection();
    }

    private void menuSelection() {
        Scanner sc = new Scanner(System.in);
        int menuNumber = 0;
        while((menuNumber < 1) | (menuNumber > 6)) {
            menuNumber = Integer.parseInt(sc.nextLine());
            switch (menuNumber) {
                case 1 -> displayAllAppointments();
                case 2 -> createAppointment();
                case 3 -> openAppointment();
                case 4 -> updateAppointment();
                case 5 -> deleteAppointment();
                case 6 -> System.exit(0);
            }
            System.out.println("Please enter a valid number");
        }
    }

    private void backToMenu() {
        System.out.println("Would you like to go back to the menu (y/n)? (no is exit program)");
        Scanner sc = new Scanner(System.in);
        if(sc.nextLine().equals("y")) {
            menu();
        }
        System.exit(0);
    }

    private void displayAllAppointments() {
        clientController.printAllAppointments();
        backToMenu();
    }

    private void createAppointment() {
        String appointmentInput = enterDates();
        clientController.createAppointment(appointmentInput);
        backToMenu();
    }

    private void openAppointment() {
        String appointmentId = enterId();
        clientController.openAppointment(appointmentId);
        backToMenu();
    }
    private void updateAppointment() {
        String appointmentId = enterId();
        String dates = enterDates();
        String appointmentInput = clientController.concatenateInputWithId(appointmentId, dates);
        clientController.updateAppointment(appointmentInput);
        backToMenu();
    }

    private void deleteAppointment() {
        String appointmentId = enterId();
        clientController.deleteAppointment(appointmentId);
        backToMenu();
    }

    private String enterDates() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a start date and time (yyyy-MM-dd HH:mm)");
        String startDate = sc.nextLine();
        System.out.println("Please enter an end date and time as (yyyy-MM-dd HH:mm)");
        String endDate = sc.nextLine();
        return clientController.concatenateInput(startDate, endDate);
    }

    private String enterId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the Appointment id");
        return sc.nextLine();
    }
}
