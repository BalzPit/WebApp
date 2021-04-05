package it.unipd.dei.webapp.resource;

import java.sql.Date;

/**
 * Represents the data about a presecription.
 */
public class Prescription {

    public final enum Status {
        pending, approved, rejected
    }

    private final int id;

    private final String doctor;

    private final String patient;

    private final Date date;

    private final String description;

    private final int duration;

    private final String type;

    private final String priority;

    private final Status status;

    /**
     * Creates a new Prescription
     * @param doctor
     * @param patient
     * @param date
     * @param description
     * @param duration
     * @param type
     * @param priority
     * @param status
     */
    public Prescription(final int id, final String doctor, final String patient, final Date date, final String description, final int duration, final String type, final String priority, final Status status) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.description = description;
        this.nprestazioni = duration;
        this.type = type;
        this.status = status;
    }

    public final String getDoctor() {
        return doctor;
    }

    public final String getPatient() {
        return patient;
    }

    public final Date getDate() {
        return date;
    }

    public final String getDescription() {
        return description;
    }

    public final int getDuration() {
        return duration;
    }

    public final Date getType() {
        return type;
    }

    public final String getPriority() {
        return priority;
    }

    public final boolean getStatus() {
        return status;
    }

}