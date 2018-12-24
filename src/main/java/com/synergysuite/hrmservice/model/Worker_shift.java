package com.synergysuite.hrmservice.model;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity

@NamedQueries({
        @NamedQuery(name = Worker_shift.GET_ALL, query = "Select ws from Worker_shift ws"),
        @NamedQuery(name = Worker_shift.GET_BY_DATE, query = "Select ws from Worker_shift ws where ws.date = ?1")
})
public class Worker_shift {

    public static final String GET_ALL = "Worker_shift.getAll";
    public static final String GET_BY_DATE = "Worker_shift.getByDate";


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "clock_in")
    private LocalDate clock_in;

    @Column(name = "clock_out")
    private LocalDate clock_out;

    @Column(name = "worker_id")
    private Long worker_id;

    @Column(name = "shift_id")
    private Long shift_id;

    @Column(name = "date")
    @FutureOrPresent
    private LocalDate date;

    @Column
    private Long branch_id;

    public Worker_shift() {
    }

    public Worker_shift(Long id, LocalDate clock_in, LocalDate clock_out, Long worker_id, Long shift_id, LocalDate date, Long branch_id) {
        this.id = id;
        this.clock_in = clock_in;
        this.clock_out = clock_out;
        this.worker_id = worker_id;
        this.shift_id = shift_id;
        this.date = date;
        this.branch_id = branch_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getClock_in() {
        return clock_in;
    }

    public void setClock_in(LocalDate clock_in) {
        this.clock_in = clock_in;
    }

    public LocalDate getClock_out() {
        return clock_out;
    }

    public void setClock_out(LocalDate clock_out) {
        this.clock_out = clock_out;
    }

    public Long getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(Long worker_id) {
        this.worker_id = worker_id;
    }

    public Long getShift_id() {
        return shift_id;
    }

    public void setShift_id(Long shift_id) {
        this.shift_id = shift_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker_shift that = (Worker_shift) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(clock_in, that.clock_in) &&
                Objects.equals(clock_out, that.clock_out) &&
                Objects.equals(worker_id, that.worker_id) &&
                Objects.equals(shift_id, that.shift_id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(branch_id, that.branch_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clock_in, clock_out, worker_id, shift_id, date, branch_id);
    }

    public Long getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Long branch_id) {
        this.branch_id = branch_id;
    }

    @Override
    public String toString() {
        return "Worker_shift{" +
                "id=" + id +
                ", clock_in=" + clock_in +
                ", clock_out=" + clock_out +
                ", worker_id=" + worker_id +
                ", shift_id=" + shift_id +
                ", date=" + date +
                ", branch_id=" + branch_id +
                '}';
    }
}
