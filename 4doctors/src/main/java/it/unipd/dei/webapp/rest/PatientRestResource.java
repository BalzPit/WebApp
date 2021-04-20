package it.unipd.dei.webapp.rest;

import it.unipd.dei.webapp.dao.PatientDAO;
import it.unipd.dei.webapp.resource.Patient;
import it.unipd.dei.webapp.utils.ErrorCode;
import org.json.JSONObject;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PatientRestResource extends RestResource {

    public PatientRestResource(HttpServletRequest req, HttpServletResponse res) {
        super(req, res, null);
    }

    // patient/list/
    public void getAllPatients() throws IOException {

        try {
            List<Patient> patients = PatientDAO.getAllPatients();
            JSONObject resJSON = new JSONObject();
            resJSON.put("patients-list", patients);
            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("application/json");
            res.getWriter().write((new JSONObject()).put("data", resJSON).toString());
        } catch (SQLException | NamingException e){
            ErrorCode ec = ErrorCode.SERVER_ERROR;
            res.setStatus(ec.getHTTPCode());
            res.setContentType("application/json");
            res.getWriter().write(ec.toJSON().toString());
        }
    }

    // patient/{patient_cf}
    public void getPatient() throws IOException {
        String op = req.getRequestURI();
        String[] tokens = op.split("/");

        try {
            String patient_cf = tokens[4];
            Patient patient = PatientDAO.searchPatientByCf(patient_cf);
            if(patient == null){
                ErrorCode ec = ErrorCode.PATIENT_NOT_FOUND;
                res.setStatus(ec.getHTTPCode());
                res.setContentType("application/json");
                res.getWriter().write(ec.toJSON().toString());
            } else {
                JSONObject resJSON = new JSONObject();
                JSONObject patientJSON = new JSONObject();
                patientJSON.put("patient", patient.toJson());
                resJSON.put("data", patientJSON);
                res.setStatus(HttpServletResponse.SC_OK);
                res.setContentType("application/json");
                res.getWriter().write(resJSON.toString());
            }
        } catch (NamingException | SQLException e){
            ErrorCode ec = ErrorCode.SERVER_ERROR;
            res.setStatus(ec.getHTTPCode());
            res.setContentType("application/json");
            res.getWriter().write(ec.toJSON().toString());
        }
    }

    // patient/{patient_cf}
    public void deletePatient() throws IOException {
        String op = req.getRequestURI();
        String[] tokens = op.split("/");

        try {
            String patient_cf = tokens[4];
            int result = PatientDAO.deletePatient(patient_cf);

            if(result == 0){
                res.setStatus(HttpServletResponse.SC_OK);
                res.setContentType("application/json");
                res.getWriter().write(new JSONObject().put("result", "successful").toString());
            } else if(result ==-1){
                res.setStatus(HttpServletResponse.SC_NOT_FOUND);
                res.setContentType("application/json");
                res.getWriter().write(new JSONObject().put("result", "error").toString());
            }
        } catch (SQLException | NamingException e){
            ErrorCode ec = ErrorCode.SERVER_ERROR;
            res.setStatus(ec.getHTTPCode());
            res.setContentType("application/json");
            res.getWriter().write(ec.toJSON().toString());
        }
    }
}
