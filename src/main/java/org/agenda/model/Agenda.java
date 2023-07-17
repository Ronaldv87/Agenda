package org.agenda.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<AgendaDay> agendaDays;

    public Agenda(List<AgendaDay> listOfAgendaDays) {
        if (listOfAgendaDays == null) {
            this.agendaDays = new ArrayList<>();
        } else {
            this.agendaDays = listOfAgendaDays;
        }
    }

    public AgendaDay insertDay(final AgendaDay dayToInsert) {
        boolean dayIsAlreadyPresent = false;
        for (final AgendaDay existingEntry : agendaDays) {
            if (existingEntry.getDate().equals(dayToInsert.getDate())) {
                dayIsAlreadyPresent = true;
                break;
            }
        }
        if (!dayIsAlreadyPresent) {
            agendaDays.add(dayToInsert);
        }
        return dayToInsert;
    }

    public List<AgendaDay> getDays() {
        return agendaDays;
    }
}
