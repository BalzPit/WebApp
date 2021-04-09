package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.MedicalExamination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This class searches for medical examinations in the database based on patient and date
 *
 * @author Pietro Balzan
 */
public final class MedicalExaminationsListDAO {

    /**
     * The SQL statement to be executed
     */
    private static final String FUTURE_EXAMINATIONS_STATEMENT = "SELECT * FROM doctors.Visita WHERE paziente = ? AND data >= ? ORDER BY data ASC";
    private static final String PAST_EXAMINATIONS_STATEMENT = "SELECT * FROM doctors.Visita WHERE paziente = ? data < ? ORDER BY data DESC";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * current date converted into a string
     */
    private final Date currentDate;

    /**
     * The patient's cf
     */
    private final String cf;



    /**
     * Creates a new object for searching medical examinations based on the CF of the patient and the set Date.
     *
     * @param con
     *            the connection to the database.
     * @param cf
     *            the patient's cf.
     * @param currentDate
     *            the date we want to use to filter the examinations db entries
     */
    public MedicalExaminationsListDAO(final Connection con, final String cf, final Date currentDate) {
        this.con = con;
        this.cf = cf;
        //this.currentDate = currentDate.toString();
        this.currentDate = currentDate;
    }



    /**
     * Get the list of FUTURE medical examinations of one patient
     *
     * @return a list of {@code Employee} object matching the salary.
     *
     * @throws SQLException
     *             if any error occurs while searching for employees.
     */
    public List<MedicalExamination> searchFutureExaminations() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<MedicalExamination> medicalExaminations = new ArrayList<MedicalExamination>();

        try {
            pstmt = con.prepareStatement(FUTURE_EXAMINATIONS_STATEMENT);
            pstmt.setString(1, cf);
            //pstmt.setString(2, currentDate);
            pstmt.setDate(2, currentDate);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                medicalExaminations.add(new MedicalExamination(rs.getString("medico"),
                        rs.getString("paziente"), rs.getDate("data"),
                        rs.getTime("ora"), rs.getString("esito")));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return medicalExaminations;
    }



    /**
     * Get the list of all PAST medical examinations of one patient
     *
     * @return a list of {@code Employee} object matching the salary.
     *
     * @throws SQLException
     *             if any error occurs while searching for employees.
     */
    public List<MedicalExamination> searchPastExaminations() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<MedicalExamination> medicalExaminations = new ArrayList<MedicalExamination>();

        try {
            pstmt = con.prepareStatement(PAST_EXAMINATIONS_STATEMENT);
            pstmt.setString(1, cf);
            //pstmt.setString(2, currentDate);
            pstmt.setDate(2, currentDate);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                medicalExaminations.add(new MedicalExamination(rs.getString("medico"),
                        rs.getString("paziente"), rs.getDate("data"),
                        rs.getTime("ora"), rs.getString("esito")));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return medicalExaminations;
    }
}
