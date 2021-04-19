package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Gender;
import it.unipd.dei.webapp.resource.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordDAO {

    /**
     * The SQL statement to be executed to retrieve the user password
     */

    public static final String SET_PASSWORD_STATEMENT = "UPDATE doctors.paziente " +
            "SET password=md5(?) " +
            "WHERE cf=?";

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
     *            the patient which wants to change password.
     */
    public PasswordDAO(final Connection con, final String cf) {
        this.con = con;
        this.cf = cf;
    }

    public void setPassword (String new_psw) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String psw = null;

        try {
            pstmt = con.prepareStatement(SET_PASSWORD_STATEMENT);
            pstmt.setString(1, new_psw);
            pstmt.setString(2, this.cf);

            pstmt.executeUpdate();

        } finally {

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
    }

}
