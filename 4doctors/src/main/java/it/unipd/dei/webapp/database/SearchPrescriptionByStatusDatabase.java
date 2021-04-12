package it.unipd.dei.webapp.database;

import it.unipd.dei.webapp.resource.Prescription;
import it.unipd.dei.webapp.resource.Prescription.Status;
import it.unipd.dei.webapp.resource.Prescription.TypeOfPrescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public final class PrescriptionManagerDatabase {

    private static final String STATEMENT = "SELECT * FROM doctors.Ricetta WHERE medico = ? AND status = ? ORDER BY data DESC";

    private final Connection con;

    private final String doctor;

    private final Status status;

    public PrescriptionManagerDatabase(final Connection con, final String doctor, final Status status) {
        this.con = con;
        this.doctor = doctor;
        this.status = status;
    }

    /**
     * Returns a list containing all prescription retrieved given a specified status
     * @return A list containing all prescription retrieved with the given status
     * @throws SQLException when an error occurs while executing the query through JDBC
     */
    public List<Prescription> getPrescriptionsByStatus() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // the results of the search
        final List<Prescription> prescriptions = new ArrayList<Prescription>();

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setString(1, doctor);
            pstmt.setObject(2, status, Types.OTHER);

            rs = pstmt.executeQuery();

            while(rs.next()) {
                prescriptions.add(new Prescription(rs.getString("id"), rs.getString("medico"), rs.getString("paziente"), rs.getDate("data"), rs.getString("descrizione"), rs.getInt("numeroprestazioni"), TypeOfPrescription.valueOf(rs.getString("tipo")), Status.valueOf(rs.getString("status"))));
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

        return prescriptions;
    }

}