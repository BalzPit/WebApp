package it.unipd.dei.webapp.rest;

import it.unipd.dei.webapp.dao.*;
import it.unipd.dei.webapp.resource.*;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manages the REST API for the {@link Medicine} resource.
 *
 * @author 4Doctors
 * @version 1.00
 * @since 1.00
 */
public final class MedicineRestResource extends RestResource {

    /**
     * Creates a new REST resource for managing {@code Medicine} resources.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @param con the connection to the database.
     */
    public MedicineRestResource(final HttpServletRequest req, final HttpServletResponse res, Connection con) {
        super(req, res, con);
    }



    /**
     * Lists all the medicines.
     *
     * @throws IOException
     *             if any error occurs in the client/server communication.
     */
    public void listMedicines() throws IOException {

        List<Medicine> medicineList  = null;
        Message m = null;

        try{
            // creates a new object for accessing the database and lists all the employees
            medicineList = new MedicineDAO(con).getListMedicines();

            if(medicineList != null) {
                res.setStatus(HttpServletResponse.SC_OK);
                new ResourceList(medicineList).toJSON(res.getOutputStream());
            } else {
                // it should not happen
                m = new Message("Cannot list employees: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            m = new Message("Cannot search employee: unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }
    }

    public void addMedicine() throws IOException{

        Message m = null;

        try{

            final Medicine medicine =  Medicine.fromJSON(req.getInputStream());

            // creates a new object for accessing the database and stores the employee
            if(new MedicineDAO(con).addMedicine(medicine)){

                res.setStatus(HttpServletResponse.SC_CREATED);

            } else {
                // it should not happen
                m = new Message("Cannot create the medicine: unexpected error.", "E5A1", null);
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        } catch (Throwable t) {
            if (t instanceof SQLException && ((SQLException) t).getSQLState().equals("23505")) {
                m = new Message("Cannot create the medicine: it already exists.", "E5A2", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            } if (t instanceof SQLException ) {
                m = new Message("Cannot create the medicine! SQL ERROR", "E5A3", t.getMessage());
                res.setStatus(HttpServletResponse.SC_CONFLICT);
                m.toJSON(res.getOutputStream());
            }
            else {
                m = new Message("Cannot create the medicine: unexpected error.", "E5A1", t.getMessage());
                res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                m.toJSON(res.getOutputStream());
            }
        }
    }
}
