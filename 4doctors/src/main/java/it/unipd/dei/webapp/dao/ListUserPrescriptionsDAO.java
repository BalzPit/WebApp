package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Medicine;
import it.unipd.dei.webapp.resource.Prescription;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class ListUserPrescriptionsDAO{
    /**
     * The SQL statement to be executed for retrieve the list of all prescriptions related to an user
     * Results are ordered in a way to show before the approved ones, the the pending and at the end
     * the rejected;
     */
    private static final String ListPrescriptions_STATEMENT = "SELECT * FROM doctors.ricetta WHERE paziente = ? ORDER BY status DESC  ";

    /**
     * The SQL statement for retrieve only the prescriptions which have a particular status given by the user
     */
    private static final String ListPrescriptions_STATEMENT_filtered = "SELECT * FROM doctors.ricetta WHERE paziente = ? AND status = ? ORDER BY data DESC  ";

    /**
     * The connection to the database
     */
    private final Connection con;

    private final String cf;

    private final String statusReq;

    /**
     * Creates a new object for checking a user into the database.
     *
     * @param con
     *            the connection to the database.
     */
    public ListUserPrescriptionsDAO(final Connection con, final String cf, final String statusReq) {
        this.con = con;
        this.cf = cf;
        this.statusReq = statusReq;
    }

    /**
     * Get the list of the prescriptions of a patient
     *
     * @throws SQLException
     *             if any error occurs while accessing the database.
     */
    public List<Prescription> listPrescriptions() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Prescription> prescriptions = new ArrayList<>();


        try {
            String id;
            String doctor;
            String patient;
            Date date;
            String description;
            int duration;
            Prescription.TypeOfPrescription type;
            Prescription.Status status;

            if(statusReq.equals("all")) {
                pstmt = con.prepareStatement(ListPrescriptions_STATEMENT);
                pstmt.setString(1, cf);
                result = pstmt.executeQuery();
            }
            else if(statusReq.equals("pending")){
                Prescription.Status pending = Prescription.Status.PENDING;
                pstmt = con.prepareStatement(ListPrescriptions_STATEMENT_filtered);
                pstmt.setString(1, cf);
                pstmt.setObject(2, pending, Types.OTHER);
                result = pstmt.executeQuery();
            }
            else if(statusReq.equals("rejected")){
                Prescription.Status rejected = Prescription.Status.REJECTED;
                pstmt = con.prepareStatement(ListPrescriptions_STATEMENT_filtered);
                pstmt.setString(1, cf);
                pstmt.setObject(2, rejected, Types.OTHER);
                result = pstmt.executeQuery();
            }
            else if(statusReq.equals("approved")){
                Prescription.Status approved = Prescription.Status.APPROVED;
                pstmt = con.prepareStatement(ListPrescriptions_STATEMENT_filtered);
                pstmt.setString(1, cf);
                pstmt.setObject(2, approved, Types.OTHER);
                result = pstmt.executeQuery();
            }

            while (result.next()){
                id=result.getString("id");
                doctor=result.getString("medico");
                patient=result.getString("paziente");
                date=result.getDate("data");
                description=result.getString("descrizione");
                duration = result.getInt("numeroprestazioni");
                type= Prescription.TypeOfPrescription.valueOf(result.getString("tipo"));
                status= Prescription.Status.valueOf(result.getString("status"));

                prescriptions.add(new Prescription(id, doctor, patient, date, description, duration, type, status));
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if(result != null){
                result.close();
            }

            con.close();
        }
        return prescriptions;
    }
}