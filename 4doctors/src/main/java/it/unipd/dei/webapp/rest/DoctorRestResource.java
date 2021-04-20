package it.unipd.dei.webapp.rest;

import it.unipd.dei.webapp.dao.DoctorDAO;
import it.unipd.dei.webapp.resource.Doctor;
import it.unipd.dei.webapp.utils.ErrorCode;
import org.json.JSONException;
import org.json.JSONObject;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class DoctorRestResource extends RestResource {

    public DoctorRestResource(HttpServletRequest req, HttpServletResponse res) {
        super(req, res, null);
    }

    // doctor/list/
    public void getAllActiveDoctors() throws IOException {

        try {
            List<Doctor> doctors = DoctorDAO.getAllActiveDoctors();
            JSONObject resJSON = new JSONObject();
            resJSON.put("doctors-list", doctors);
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

    // doctor/{doctor_cf}
    public void getDoctor() throws IOException {
        String op = req.getRequestURI();
        String[] tokens = op.split("/");

        try {
            String doctor_cf = tokens[4];
            Doctor doctor = DoctorDAO.searchDoctorByCF(doctor_cf);
            if(doctor == null){
                ErrorCode ec = ErrorCode.DOCTOR_NOT_FOUND;
                res.setStatus(ec.getHTTPCode());
                res.setContentType("application/json");
                res.getWriter().write(ec.toJSON().toString());
            } else {
                JSONObject resJSON = new JSONObject();
                JSONObject doctorJSON = new JSONObject();
                doctorJSON.put("doctor", doctor.toJson());
                resJSON.put("data", doctorJSON);
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

    // doctor/
    public void createDoctor() throws IOException {

        try {
            Doctor doctor = Doctor.fromJSON(req.getInputStream());
            int result = DoctorDAO.createDoctor(doctor);
            if(result == 0) {
                JSONObject resJSON = new JSONObject();
                resJSON.put("result", "successful");
                res.setStatus(HttpServletResponse.SC_OK);
                res.setContentType("application/json");
                res.getWriter().write(resJSON.toString());
            }
            else if(result == -1) {
                ErrorCode ec = ErrorCode.SERVER_ERROR;
                res.setStatus(ec.getHTTPCode());
                res.setContentType("application/json");
                res.getWriter().write(ec.toJSON().toString());
            }
        } catch (JSONException e) {
            ErrorCode ec = ErrorCode.WRONG_JSON_FORMAT;
            res.setStatus(ec.getHTTPCode());
            res.setContentType("application/json");
            res.getWriter().write(ec.toJSON().toString());
        } catch (ParseException e){
            ErrorCode ec = ErrorCode.WRONG_DATA_FORMAT;
            res.setStatus(ec.getHTTPCode());
            res.setContentType("application/json");
            res.getWriter().write(ec.toJSON().toString());
        } catch (SQLException | NamingException e){
            ErrorCode ec = ErrorCode.SERVER_ERROR;
            res.setStatus(ec.getHTTPCode());
            res.setContentType("application/json");
            res.getWriter().write(ec.toJSON().toString());
        }
    }

    // doctor/{doctor_cf}
    public void updateDoctorStatus() throws IOException {
        String op = req.getRequestURI();
        String[] tokens = op.split("/");

        try {
            String doctor_cf = tokens[4];
            int result = DoctorDAO.updateDoctorStatus(doctor_cf);
            if(result == 0){
                JSONObject resJSON = new JSONObject();
                res.setStatus(HttpServletResponse.SC_OK);
                res.setContentType("application/json");
                resJSON.put("result", "successful");
                res.getWriter().write(resJSON.toString());
            }
            else if(result == -1){
                ErrorCode ec = ErrorCode.SERVER_ERROR;
                res.setStatus(ec.getHTTPCode());
                res.setContentType("application/json");
                res.getWriter().write(ec.toJSON().toString());
            }
            else if(result == -2){
                ErrorCode ec = ErrorCode.DOCTOR_NOT_FOUND;
                res.setStatus(ec.getHTTPCode());
                res.setContentType("application/json");
                res.getWriter().write(ec.toJSON().toString());
            }
        } catch (SQLException | NamingException e){
            ErrorCode ec = ErrorCode.SERVER_ERROR;
            res.setStatus(ec.getHTTPCode());
            res.setContentType("application/json");
            res.getWriter().write(ec.toJSON().toString());
        }
    }

    // doctor/{doctor_cf}
    public void deleteDoctor() throws IOException {
        String op = req.getRequestURI();
        String[] tokens = op.split("/");

        try {
            String doctor_cf = tokens[4];
            int result = DoctorDAO.deleteDoctor(doctor_cf);

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
