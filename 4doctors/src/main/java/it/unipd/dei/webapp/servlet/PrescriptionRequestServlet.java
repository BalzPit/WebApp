package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.dao.PrescriptionRequestDAO;
import it.unipd.dei.webapp.resource.Message;
import it.unipd.dei.webapp.resource.Prescription;
import it.unipd.dei.webapp.utils.InputFormatException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


/**
 * Creates a new patient into the database.
 */
public final class PrescriptionRequestServlet extends AbstractDatabaseServlet {

    /**
     * Creates a new patient into the database.
     *
     * @param req
     *            the HTTP request from the client.
     * @param res
     *            the HTTP response from the server.
     *
     * @throws ServletException
     *             if any error occurs while executing the servlet.
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String patient_cf = (String) session.getAttribute("cf");

        // request parameters
        String doctor_cf = null;
        String description = null;
        String type = null;

        //other prescription info
        Date date = null;  //should be use the request date, format: yyyy-mm-dd
        Prescription.Status status = Prescription.Status.PENDING; //must be set as pending, we are just instantiate the request
        int duration = 0; //the doctor will change this parameter when he accept the request

        // model
        Prescription prescription = null;
        Message message = null;

        try{
            // retrieves the request parameters
            doctor_cf = req.getParameter("doctor cf");  //todo: invece di chiedere il cf del dottore (come fa a saperlo scrive nome e cognome e lo cerco io il cf
            description = req.getParameter("description");
            type = req.getParameter("type");

            // Check if some parameters are null
            if(doctor_cf == null || patient_cf == null || description == null || type == null ) {
                throw new InputFormatException("One or more input parameters are null");
            }

            // Check if some parameters are empty
            if(doctor_cf.isEmpty() || patient_cf.isEmpty() || description.isEmpty() || type.isEmpty() ) {
                throw new InputFormatException("One or more input parameters are empty");
            }

            // Removing leading and trailing white space
            doctor_cf = doctor_cf.trim().toUpperCase();
            patient_cf = patient_cf.trim().toUpperCase();
            description = description.trim();

            // Check if cf has a bad format
            if(doctor_cf.length() != 16 || doctor_cf.contains(" ")){
                throw new InputFormatException("The format of the parameter Codice Fiscale is wrong");
            }
            if(patient_cf.length() != 16 || patient_cf.contains(" ")){
                throw new InputFormatException("The format of the parameter Codice Fiscale is wrong");
            }

            date = new Date(System.currentTimeMillis());

            // Create a new Gender enum with the gender type retrieved from the form
            Prescription.TypeOfPrescription typeEnum = Prescription.TypeOfPrescription.valueOf(type);

            // creates a new patient from the request parameters
            prescription = new Prescription(doctor_cf, patient_cf, date, description, duration, typeEnum, status);

            // creates a new object for accessing the database and stores the patient
            new PrescriptionRequestDAO(getDataSource().getConnection(), prescription).prescriptionRequest();

            message = new Message("Prescription successfully forwarded to the doctor.");

        } catch (InputFormatException ex) {
            message = new Message("Cannot forward the request. Invalid input parameters",
                    "E100", ex.getMessage());
        } catch (IllegalArgumentException ex){
            message = new Message("Cannot forward the request: gender type is wrong.",
                    "E102", ex.getMessage());
        } catch (SQLException ex) {
            if (ex.getSQLState().equals("23505")) {
                message = new Message("Cannot forward the request: SQLException", "E300", ex.getMessage());
            } else {
                message = new Message("Cannot forward the request: unexpected error while accessing the database.",
                        "E200", ex.getMessage());
            }
        }

        req.setAttribute("message", message);
        req.setAttribute("cf", patient_cf);

        // forwards the control
        req.getRequestDispatcher("/protected/jsp/prescription/prescription-home.jsp").forward(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String patient_cf = (String) session.getAttribute("cf");
        req.setAttribute("cf", patient_cf);

        // forwards the control
        req.getRequestDispatcher("/protected/jsp/prescription/prescription-home.jsp").forward(req, res);
    }
}