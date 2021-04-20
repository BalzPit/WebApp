package it.unipd.dei.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unipd.dei.webapp.resource.MedicalExamination;

/**
 * creates a new medical examination to be added to the database as a "Visita"
 *
 * @author Pietro Balzan
 */

public class CreateMedicalExaminationDAO {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO doctors.Visita (medico, paziente, data, ora, esito) VALUES (?, ?, ?, ?, ?) RETURNING *";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * The medical examination to be stored into the database
     */
    private final MedicalExamination med_ex;



    /**
     * Public constructor that creates a new object for storing a medical examination into the database.
     *
     * @param con
     *            the connection to the database.
     * @param med_ex
     *            the examination to be stored into the database.
     */
    public CreateMedicalExaminationDAO(final Connection con, final MedicalExamination med_ex) {
        this.con = con;
        this.med_ex = med_ex;
    }



    /**
     * Stores a new medical examination into the database
     *
     * @throws SQLException
     *             if any error occurs while storing the examination.
     */
    public MedicalExamination createMedicalExamination() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //the created examination
        MedicalExamination medEx = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, med_ex.getDoctor_cf());
            pstmt.setString(2, med_ex.getPatient_cf());
            pstmt.setDate(3, med_ex.getDate());
            pstmt.setTime(4, med_ex.getTime());
            pstmt.setString(5, med_ex.getOutcome());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                medEx = new MedicalExamination( rs.getString("doctor_cf"),
                                                rs.getString("patient_cf"),
                                                rs.getDate("date"),
                                                rs.getTime("time"),
                                                rs.getString("outcome"));
            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

        return medEx;
    }

}
