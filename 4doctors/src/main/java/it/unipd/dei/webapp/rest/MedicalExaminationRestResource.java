package it.unipd.dei.webapp.rest;

import it.unipd.dei.webapp.dao.CreateMedicalExaminationDAO;
import it.unipd.dei.webapp.dao.MedExDAO;
import it.unipd.dei.webapp.resource.BookingTime;
import it.unipd.dei.webapp.resource.MedicalExamination;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.ResourceList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MedicalExaminationRestResource extends RestResource{

    /**
     * Creates a new REST resource for Medical Examinations resources.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public MedicalExaminationRestResource(HttpServletRequest req, HttpServletResponse res, Connection con) {
        super(req, res, con);
    }






    /**
     * creates a new Medical Examination into the database
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void createMedicalExamination() throws IOException{
        MedicalExamination mE = null;
        Message m = null;

        try{
            final MedicalExamination medicalExamination = MedicalExamination.fromJSON(req.getInputStream());

            //check if date is valid (has to be in the future)
            Date currentDate = new java.sql.Date(new java.util.Date().getTime());
            //check time is valid
            BookingTime bookingTime = new BookingTime(medicalExamination.getTime(), true);

            //invalid parameters
            if(medicalExamination.getDate().compareTo(currentDate) < 0 ||  bookingTime.getMin() == null
                    ||  bookingTime.getHour() == null) {
                m = new Message(
                        "Cannot create the examination: date or time are not valid.",
                        "E4A7", String.format("Request date: %s, time: %s; need future date and time to be among the acceptable inputs.",
                        medicalExamination.getDate(), medicalExamination.getTime()));
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                m.toJSON(res.getOutputStream());
                return;
            }

            // stores the examination
            mE = MedExDAO.createMedicalExamination(medicalExamination);

            if(mE != null) {
                res.setStatus(HttpServletResponse.SC_CREATED);
                mE.toJSON(res.getOutputStream());
            } else {
                // it should not happen
                m = new Message("Cannot create the medical examination: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23505")) {
                m = new Message("Cannot create the medical examination: it already exists.", "E5A2", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot create the medical examination: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }






    /**
     * Reads a medical examination from the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void readMedicalExamination() throws IOException {

        MedicalExamination mE = null;
        Message m = null;

        try{
            // parse the URI path to extract the parameters
            String path = req.getRequestURI();
            String examParam = path.substring(path.lastIndexOf("medicalExamination") + 18+1); //+1 to skip the first "/"

            String delim = "[/]"; //split the string at the slashes
            String[] params = examParam.split(delim);

            if(params.length != 3){
                //failed to split
                m = new Message(String.format("\"Cannot read examination: error while parsing URI. Examination from %s on %s at %s not found.", params[0], params[1], params[2]), "E5A7", "wrong parameters structure");
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                m.toJSON(res.getOutputStream());
                return;
            }

            final String doctor_cf = params[0];
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(params[1]);
            final Date date = new java.sql.Date(utilDate.getTime());
            java.util.Date utilTime = new SimpleDateFormat("hh:mm").parse(params[2]);
            final Time time = new java.sql.Time(utilTime.getTime());

            // creates a new object for accessing the database and reads the examination
            mE = MedExDAO.readMedicalExamination(doctor_cf, date, time);

            if(mE != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                mE.toJSON(res.getOutputStream());
            } else {
                m = new Message(String.format("Examination from %s on %s at %s not found.", doctor_cf, date, time), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot read examination: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }






    /**
     * Updates a medical examination in the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void updateMedicalExaminationOutcome() throws IOException {

        MedicalExamination mE = null;
        Message m = null;

        try{

            // parse the URI path to extract the parameters
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("medicalExamination") + 18+1); //+1 to skip the first "/"

            String delim = "[/]"; //split the string at the slashes
            String[] params = path.split(delim);

            if(params.length != 3){
                //failed to split
                m = new Message("Cannot read examination to update: error while parsing URI.", "E5A7", "wrong parameters structure");
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                m.toJSON(res.getOutputStream());
                return;
            }

            //get request parameters
            final String doctor_cf = params[0];
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(params[1]);
            final Date date = new java.sql.Date(utilDate.getTime());
            java.util.Date utilTime = new SimpleDateFormat("hh:mm").parse(params[2]);
            final Time time = new java.sql.Time(utilTime.getTime());

            final MedicalExamination updatedExamination = MedicalExamination.fromJSON(req.getInputStream());

            //can only change the outcome of the examination
            if (!doctor_cf.equals(updatedExamination.getDoctor_cf())    ||
                     date.compareTo(updatedExamination.getDate()) != 0  ||
                     time.compareTo(updatedExamination.getTime()) != 0) {
                m = new Message(
                        "Wrong request for URI /medicalExamination/{doctor_cf}/{date}/{time}: URI request and med ex resource differ.",
                        "E4A7", String.format("Request URI doctor_cf: %s, date: %s, time: %s; med ex resource doctor_cf: %s, date: %s, time: %s.",
                        doctor_cf, date, time, updatedExamination.getDoctor_cf(), updatedExamination.getDate(), updatedExamination.getTime()));
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                m.toJSON(res.getOutputStream());
                return;
            }

            // creates a new object for accessing the database and updates the medical examination
            mE = MedExDAO.updateMedicalExaminationOutcome(updatedExamination);

            if(mE != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                mE.toJSON(res.getOutputStream());
            } else {
                m = new Message(String.format("Examination from %s on %s at %s not found.", doctor_cf, date, time), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23503")) {
                m = new Message("Cannot update the examination: other resources depend on it.", "E5A4", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot update the examination: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }






    /**
     * Searches medical examinations depending on the patient.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void searchMedicalExaminationByPatient()  throws IOException {

        ArrayList<MedicalExamination> medicalExaminationList = null;
        Message m = null;
        String patientCf = "epic";

        try{
            // parse the URI path to extract the patient's CF
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("patient") + 7);

            //get cf
            //final String patientCf = path.substring(1);
            patientCf = path.substring(1);

            // creates a new object for accessing the database and search the medical examination
            medicalExaminationList = MedExDAO.getMedicalExaminations(patientCf);

            if(medicalExaminationList != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(medicalExaminationList).toJSON(res.getOutputStream());
            } else {
                // it should not happen
                m = new Message("Cannot search medical examinations: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message(String.format("Patient_cf = %s, Cannot search medical examination: unexpected error.", patientCf), "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }






    /**
     * Deletes a medical examination from the database.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void deleteMedicalExamination() throws IOException {

        MedicalExamination mE = null;
        Message m = null;

        try{

            // parse the URI path to extract the examination
            String path = req.getRequestURI();
            path = path.substring(path.lastIndexOf("medicalExamination") + 18+1); //+1 to skip the first "/"

            String delim = "[/]"; //split the string at the slashes
            String[] params = path.split(delim);

            if(params.length != 3){
                //failed to split
                m = new Message("Cannot read examination to delete: error while parsing URI.", "E5A7", "wrong parameters structure");
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                m.toJSON(res.getOutputStream());
                return;
            }

            //get request parameters
            final String doctor_cf = params[0];
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(params[1]);
            final Date date = new java.sql.Date(utilDate.getTime());
            java.util.Date utilTime = new SimpleDateFormat("hh:mm").parse(params[2]);
            final Time time = new java.sql.Time(utilTime.getTime());

            // delete the medical examination
            mE = MedExDAO.deleteMedicalExamination(doctor_cf, date, time);

            if(mE != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                mE.toJSON(res.getOutputStream());
            } else {
                m = new Message(String.format("Examination from %s on %s at %s not found.", doctor_cf, date, time), "E5A3", null);
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23503")) {
                m = new Message("Cannot delete the medical examination: other resources depend on it.", "E5A4", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } else {
                m = new Message("Cannot delete the medical examination: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }

}
