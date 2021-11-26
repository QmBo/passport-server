package ru.job4j.passport.server.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Passport class.
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 26.11.2021
 */
@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private Long serial;
    @Column(name = "end_date")
    private Date endDate;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public Passport setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Gets serial.
     *
     * @return the serial
     */
    public Long getSerial() {
        return serial;
    }

    /**
     * Sets serial.
     *
     * @param serial the serial
     * @return the serial
     */
    public Passport setSerial(Long serial) {
        this.serial = serial;
        return this;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     * @return the end date
     */
    public Passport setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Passport passport = (Passport) o;
        return Objects.equals(id, passport.id)
                && Objects.equals(serial, passport.serial)
                && Objects.equals(endDate, passport.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serial, endDate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Passport.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("serial=" + serial)
                .add("endDate=" + endDate)
                .toString();
    }

    /**
     * Check passport not null serial and end date.
     *
     * @return the boolean
     */
    public boolean check() {
        boolean result = true;
        if (this.serial == null || this.endDate == null) {
            result = false;
        }
        return result;
    }
}
