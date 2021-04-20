package it.unipd.dei.webapp.servlet;


import it.unipd.dei.webapp.dao.MedicineDAO;
import it.unipd.dei.webapp.resource.Medicine;
import it.unipd.dei.webapp.resource.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import java.util.List;


public final class ListMedicinesServlet extends AbstractDatabaseServlet {

    /**
     * Show all medicines available
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
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        String user_cf = (String) session.getAttribute("cf");

        // model
        List<Medicine> medicineList  = null;
        Message message = null;

        try{
            // creates a new object for accessing the database and stores the patient
            MedicineDAO show = new MedicineDAO(getDataSource().getConnection());
            medicineList = show.getListMedicines();
            message = new Message("Medicine succesfully searched!");

        } catch (SQLException ex) {
            message = new Message("Cannot search for employees: unexpected error while accessing the database.",
                    "E200", ex.getMessage());
        }

        req.setAttribute("medicineList", medicineList);
        req.setAttribute("message", message);
        req.setAttribute("cf", user_cf);

        req.getRequestDispatcher("/protected/jsp/medicines/medicine-list.jsp").forward(req, res);


    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        doGet(req,res);
    }

}