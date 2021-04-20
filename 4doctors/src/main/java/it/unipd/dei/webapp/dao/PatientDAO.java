package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Patient;
import it.unipd.dei.webapp.utils.DataSourceProvider;

import javax.naming.NamingException;
import java.sql.*;

/**
 * Class containing all the useful operation for the resource patient in the database
 */
public class PatientDAO {

    /**
     * Stores a new patient into the database
     *
     *
     * @param patient the patient to store in the database
     * @throws SQLException
     *        if any error occurs while storing the patient.
     */
    public static void createPatient(Patient patient) throws SQLException, NamingException {

        final String STATEMENT = "INSERT INTO doctors.Paziente (cf, nome, cognome, email, password, sesso, datanascita, luogonascita, " +
                "indirizzoresidenza) VALUES (?, ?, ?, ?, MD5(?), ?, ?, ?, ?)";

        try (Connection con = DataSourceProvider.getDataSource().getConnection()) {

            PreparedStatement pstmt = con.prepareStatement(STATEMENT);

            pstmt.setString(1, patient.getCf());
            pstmt.setString(2, patient.getName());
            pstmt.setString(3, patient.getSurname());
            pstmt.setString(4, patient.getEmail());
            pstmt.setString(5, patient.getPassword());
            pstmt.setObject(6, patient.getGender(), Types.OTHER);
            pstmt.setDate(7, patient.getBirthday());
            pstmt.setString(8, patient.getBirthplace());
            pstmt.setString(9, patient.getAddress());

            pstmt.execute();
        }
    }

    /*
    TODO: I DUE METODI SUCCESSIVI RESTITUISCONO SOLO
        UN BOOLEANO PER VERIFICARE CHE L'UTENTE SIA PRESENTE.
        SE LO MODIFICATE PERCHE VI SERVE L'OGGETTO Patient ALLORA BISOGNA SISTEMARE
        LA PARTE DI CODICE RELATIVA IN CreatePatientServlet
     */

    /**
     * Search a patient by codice fiscale
     *
     * @param cf the codice fiscale of a patient
     * @return true if a patient is found
     * @throws SQLException if any error occurs while finding the patient.
     */
    public static boolean searchPatientByCf(String cf) throws SQLException, NamingException {

        final String STATEMENT = "SELECT * FROM doctors.Paziente WHERE cf=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        boolean found = false;

        try {

            con = DataSourceProvider.getDataSource().getConnection();
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, cf);

            result = pstmt.executeQuery();

            if(result.next())
                found = true;

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if(result != null){
                result.close();
            }

            con.close();
        }
        return found;
    }

    /**
     * Search a patient by email
     *
     * @param email the email of a patient
     * @return true if a patient is found
     * @throws SQLException if any error occurs while finding the patient.
     */
    public static boolean searchPatientByEmail(String email) throws SQLException, NamingException {

        final String STATEMENT = "SELECT * FROM doctors.Paziente WHERE email=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        boolean found = false;

        try {

            con = DataSourceProvider.getDataSource().getConnection();
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, email);

            result = pstmt.executeQuery();

            if(result.next())
                found = true;

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if(result != null){
                result.close();
            }

            con.close();
        }
        return found;
    }
}
