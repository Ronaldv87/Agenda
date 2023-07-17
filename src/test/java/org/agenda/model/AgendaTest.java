package org.agenda.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {

    private Agenda agenda;

    @BeforeEach
    void setup() {
        this.agenda = new Agenda(createListOfDays(7));
    }

    @AfterEach
    void teardown() {
        this.agenda = null;
    }

    @Test
    void insertDay_insertsDay_whenDayIsNotPresent() {
        //Given
        this.agenda = new Agenda(new ArrayList<>());
        final LocalDate localDate = LocalDate.now().minusDays(5);
        final AgendaDay dayToInsert = new AgendaDay(localDate);

        //When
        final AgendaDay insertedDay = agenda.insertDay(dayToInsert);

        //Then
        assertNotNull(insertedDay);
        assertEquals(1, agenda.getDays().size());
    }

    @Test
    void insertDay_doesNotInsertDay_whenDayIsAlreadyPresent() {
        //Given
        final LocalDate localDate = LocalDate.now();
        final AgendaDay dayToInsert = new AgendaDay(localDate);

        //When
        final AgendaDay insertedDay = agenda.insertDay(dayToInsert);

        //Then
        assertNotNull(insertedDay);
        assertEquals(7, agenda.getDays().size());
    }

    private List<AgendaDay> createListOfDays(final int amountOfDays) {
        final List<AgendaDay> dayList = new ArrayList<>();
        for(int n = 0; n < amountOfDays; n++) {
            dayList.add(createDay(n));
        }
        return dayList;
    }

    private AgendaDay createDay(final int daysToSubtract) {
        final LocalDate localDate = LocalDate.now();
        return new AgendaDay(localDate.minusDays(daysToSubtract));
    }
}