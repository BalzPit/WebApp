package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Patient;
import it.unipd.dei.webapp.resource.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ProfileDAO {

    /**
     * The SQL statement to be executed to retrieve the user personal information
     */

    public static final String GET_PERSONAL_INFO_STATEMENT = "SELECT cf, nome, cognome, email, password, sesso, datanascita, luogonascita, indirizzoresidenza FROM doctors.Paziente " +
            "WHERE cf = ? ";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The patient to which the medical exainations are displayed
     */
    private final String cf;

    /**
     * Creates a new object for storing a patient into the database.
     *
     * @param con
     *            the connection to the database.
     * @param cf
     *            the patient to which the medical exainations are displayed.
     */
    public ProfileDAO(final Connection con, final String cf) {
        this.con = con;
        this.cf = cf;
    }

    /**
     * Get the medical examinations of the patient
     *
     * @throws SQLException
     *             if any error occurs while storing the employee.
     */
    public Patient getPatient() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Patient patient = null;

        try {
            pstmt = con.prepareStatement(GET_PERSONAL_INFO_STATEMENT);
            pstmt.setString(1, this.cf);

            rs = pstmt.executeQuery();

            rs.next();

            patient = new Patient(
                    rs.getString("cf"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getDate("datanascita"),
                    rs.getString("luogonascita"),
                    rs.getString("indirizzoresidenza"),
                    Gender.valueOf(rs.getString("sesso"))
            );

        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return patient;
    }
}
