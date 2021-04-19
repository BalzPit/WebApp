package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Check if a user is in the database
 */
public class LoginDAO {

    /**
     * The SQL statement to be executed for the patient
     */
    private static final String PATIENT_STATEMENT = "SELECT * FROM doctors.Paziente WHERE cf=? AND password=md5(?)";

    /**
     * The SQL statement to be executed for the medic
     */
    private static final String MEDIC_STATEMENT = "SELECT * FROM doctors.Medico WHERE cf=? AND password=md5(?)";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The cf of the user
     */
    private final String cf;

    /**
     * The password of the user
     */
    private final String password;

    /**
     * The role of the user
     */
    private final String role;

    /**
     * Creates a new object for checking a user into the database.
     *
     * @param con the connection to the database.
     * @param cf the cf of the user.
     * @param password the password of the user.
     * @param role the role of the user.
     */
    public LoginDAO(final Connection con, final String cf, final String password, final String role) {
        this.con = con;
        this.cf = cf;
        this.password = password;
        this.role = role;
    }

    /**
     * Check if a user is in the database
     *
     * @throws SQLException
     *             if any error occurs while accessing the database.
     */
    public boolean authenticateUser() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet result = null;
        boolean authenticated = false;

        try {
            if(role.equals("patient")){
                pstmt = con.prepareStatement(PATIENT_STATEMENT);
            }
            else if(role.equals("doctor")){
                pstmt = con.prepareStatement(MEDIC_STATEMENT);
            }
            else {
                con.close();
                return false;
            }

            pstmt.setString(1, cf);
            pstmt.setString(2, password);
            result = pstmt.executeQuery();

            // Check if a user has been retrieved from database
            if(result.next()){
                authenticated = true;
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
        return authenticated;
    }
}
