package com.synergysuite.hrmservice.model;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "shifts")
@NamedQueries({
        @NamedQuery(name = Shift.GET_ALL_SHIFTS, query = "SELECT s from Shift s"),
        @NamedQuery(name = Shift.GET_SHIFT_BY_ID, query = "SELECT s from Shift s where s.id=?1")
})
public class Shift {

    public static final String GET_ALL_SHIFTS = "Shift.getAllShifts";
    public static final String GET_SHIFT_BY_ID = "Shift.getShiftById";

    @Id
    @GeneratedValue
    private Long id;
    @Basic
    private java.sql.Time start;
    @Basic
    private java.sql.Time end;

    public Shift() {
    }

    public Shift(Long id, Time start, Time end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }


    public Shift(Time start, Time end) {
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }
}
