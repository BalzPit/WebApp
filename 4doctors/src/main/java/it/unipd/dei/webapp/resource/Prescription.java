package it.unipd.dei.webapp.resource;

import java.sql.Date;

// Represents the data about a prescription
public class Prescription {

    public enum Status {
        PENDING, APPROVED, REJECTED
    }

    public enum TypeOfPrescription {
        ESAME, FARMACO
    }

    // The id of the prescription in postgre uuid format
    private final String id;

    // The cf of the doctor who gets the prescription request
    private final String doctor;

    // The cf of the patient who requests the prescription
    private final String patient;

    // The date when the prescription request has been done
    private final Date date;

    // A short description about the motivation of the prescription request
    private final String description;

    // The duration of the prescription
    private final int duration;

    // The type of the prescription. It can be either EXAM or MEDICINE according to what the patient has requested
    private final TypeOfPrescription type;

    /* The status of the presecription. It can be either
        - PENDING when the request has been sent
        - APPORVED when the doctor accepts it
        - REJECETED when the doctor refuses it
    */
    private final Status status;

    /**
     * Creates a new Prescription
     * @param id The id of the prescription in postgre uuid format
     * @param doctor The cf of the doctor who gets the prescription request
     * @param patient The cf of the patient who requests the prescription
     * @param date The date when the prescription request has been done
     * @param description A short description about the motivation of the presciption request
     * @param duration The duration of the prescription
     * @param type The type of the prescription
     * @param status The status of the presecription
     */
    public Prescription(final String id, final String doctor, final String patient, final Date date, final String description, final int duration, final TypeOfPrescription type, final Status status) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.description = description;
        this.duration = duration;
        this.type = type;
        this.status = status;
    }

    /**
     * Creates a new presciption without knowing which uuid will be assigned
     * @param doctor The cf of the doctor who gets the prescription request
     * @param patient The cf of the patient who requests the prescription
     * @param date The date when the prescription request has been done.
     * @param description A short description about the motivation of the presciption request
     * @param duration The duration of the prescription
     * @param type The type of the prescription
     * @param status The status of the presecription
     */
    public Prescription(final String doctor, final String patient, final Date date, final String description, final int duration, final TypeOfPrescription type, final Status status) {
        this(null, doctor, patient, date, description, duration, type, status);
    }

    /**
     * Returns the id of the prescription in uuid formad
     * @return the id of the prescription in uuid formad
     */
    public final String getId() {
        return id;
    }

    /**
     * Returns the type of the prescription
     * @return the prescription's doctor cf
     */
    public final String getDoctor() {
        return doctor;
    }

    /**
     * Returns the type of the prescription
     * @return the prescription's patient cf
     */
    public final String getPatient() {
        return patient;
    }

    /**
     * Returns the date of the prescription
     * @return the date of the prescription
     */
    public final Date getDate() {
        return date;
    }

    /**
     * Returns the description of the prescription
     * @return the description of the prescription
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Returns the duration of the prescription
     * @return the duration of the prescription
     */
    public final int getDuration() {
        return duration;
    }

    /**
     * Returns the type of the prescription
     * @return the type of the prescription
     */
    public final TypeOfPrescription getType() {
        return type;
    }

    /**
     * Returns the status of the user
     * @return the status of the user
     */
    public final Status getStatus() {
        return status;
    }

}