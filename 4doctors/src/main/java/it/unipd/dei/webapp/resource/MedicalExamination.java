package it.unipd.dei.webapp.resource;
import java.sql.Date;
import java.sql.Time;

/**
 * Represents the data about a medical examination.
 */
public class MedicalExamination {

    /**
     * The codice fiscale of the doctor
     */
    private final String doctor_cf;

    /**
     * The codice fiscale of the patient
     */
    private final String patient_cf;

    /**
     * The date of the medical examination
     */
    private final Date date;

    /**
     * The time of the medical examination
     */
    private final Time time;

    /**
     * The outcome of the medical examination
     */
    private final String outcome;

    /**
     * Creates a new medical examination
     *
     * @param doctor_cf    the codice fiscale of the doctor
     * @param patient_cf   the codice fiscale of the patient
     * @param date   the date of a medical examination
     * @param time   the time of a medical examination
     */
    public MedicalExamination(final String doctor_cf, final String patient_cf, final Date date, final Time time, final String outcome){
        this.doctor_cf = doctor_cf;
        this.patient_cf = patient_cf;
        this.date = date;
        this.time = time;
        this.outcome = outcome;
    }

    /**
     * Returns the codice fiscale of the doctor.
     *
     * @return the codice fiscale of the doctor.
     */
    public String getDoctor_cf() {
        return doctor_cf;
    }

    /**
     * Returns the codice fiscale of the patient.
     *
     * @return the codice fiscale of the patient.
     */
    public String getPatient_cf() {
        return patient_cf;
    }

    /**
     * Returns the date of the medical examination.
     *
     * @return the date of the medical examination.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the time of the medical examination.
     *
     * @return the time of the medical examination.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Returns the time of the medical examination.
     *
     * @return the time of the medical examination.
     */
    public String getOutcome() {
        return outcome;
    }
}
