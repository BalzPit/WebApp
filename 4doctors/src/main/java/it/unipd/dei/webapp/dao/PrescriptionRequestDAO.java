package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Prescription;

import java.sql.*;
import java.util.UUID;

/**
 * Creates a new patient into the database.
 */
public final class PrescriptionRequestDAO {

    /**
     * The SQL statement to be executed
     */
    private static final String STATEMENT = "INSERT INTO doctors.ricetta (id, medico, paziente, data, descrizione, numeroprestazioni, tipo, status) VALUES ( ?, (SELECT cf FROM doctors.medico WHERE email=?), ?, ?, ?, ?, ?, ?)";

    private static final String STATEMENT_RICETTAESAME = "INSERT INTO doctors.ricettaesame (esame, ricetta) VALUES (?, ?)";

    private static final String STATEMENT_RICETTAFARMACO = "INSERT INTO doctors.farmaciricetta (farmaco, ricetta, qta) VALUES (?, ?, ?)";

    /**
     * The connection to the database
     */
    private final Connection con;

    private final Prescription prescription;

    private final String email;

    private final String code;

    private final int qnt;


    public PrescriptionRequestDAO(final Connection con, final Prescription prescription, final String email, final String code,final int qnt) {
        this.con = con;
        this.prescription = prescription;
        this.email = email;
        this.code = code;
        this.qnt = qnt;
    }

    /**
     * Stores a new patient into the database
     *
     * @throws SQLException
     *             if any error occurs while storing the employee.
     */
    public void prescriptionRequest() throws SQLException {

        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        UUID id = null;
        int index;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            id = UUID.randomUUID();
            index = 1;
            pstmt.setObject(index++, id);
            pstmt.setString(index++, email);
            pstmt.setString(index++, prescription.getPatient());
            pstmt.setDate(index++, prescription.getDate());
            pstmt.setString(index++, prescription.getDescription());
            pstmt.setInt(index++, prescription.getDuration());
            pstmt.setObject(index++, prescription.getType(), Types.OTHER);
            pstmt.setObject(index, prescription.getStatus(), Types.OTHER);

            pstmt.execute();

            if(prescription.getType()==Prescription.TypeOfPrescription.ESAME){
                pstmt1 = con.prepareStatement(STATEMENT_RICETTAESAME);
                index = 1;
                pstmt1.setString(index++, code);
                pstmt1.setObject(index, id);

                pstmt1.execute();

            }
            else if(prescription.getType()==Prescription.TypeOfPrescription.FARMACO){
                pstmt1 = con.prepareStatement(STATEMENT_RICETTAFARMACO);
                index = 1;
                pstmt1.setString(index++, code);
                pstmt1.setObject(index++, id);
                pstmt1.setInt(index, qnt);

                pstmt1.execute();

            }

        } finally {
            if (pstmt != null) {
                pstmt.close();
                pstmt1.close();
            }

            con.close();
        }

    }
}