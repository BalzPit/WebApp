package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Prescription;

import java.sql.*;

/**
 * Creates a new patient into the database.
 */
public final class PrescriptionRequestDAO {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO doctors.ricetta (medico, paziente, data, descrizione, tipo, status) VALUES ( ?, ?, ?, ?, ?, ?);";

    /**
     * The connection to the database
     */
    private final Connection con;

    private final Prescription prescription;


    public PrescriptionRequestDAO(final Connection con, final Prescription prescription ) {
        this.con = con;
        this.prescription = prescription;
    }

    /**
     * Stores a new patient into the database
     *
     * @throws SQLException
     *             if any error occurs while storing the employee.
     */
    public void prescriptionRequest() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, prescription.getDoctor());
            pstmt.setString(2, prescription.getPatient());
            pstmt.setDate(3, prescription.getDate());
            pstmt.setString(4, prescription.getDescription());
            pstmt.setObject(5, prescription.getType(), Types.OTHER);
            pstmt.setObject(6, prescription.getStatus(), Types.OTHER);

            pstmt.execute();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

    }
}