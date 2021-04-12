package it.unipd.dei.webapp.database;

import it.unipd.dei.webapp.resource.Prescription;
import it.unipd.dei.webapp.resource.Prescription.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public final class PrescriptionUpdateDatabase {

    private static final String STATEMENT = "UPDATE doctors.Ricetta SET status = ? WHERE id = ?";

    private final Connection con;
    private final int id;
    private final Status status;

    public PrescriptionUpdateDatabase(final Connection con, final int id, final Status status) {
        this.con = con;
        this.id = id;
        this.status = status;
    }

    /**
     * Update the status of the prescription corresponding to the id given in the constructor
     * @throws SQLException SQLException when an error occurs while executing the query through JDBC
     */
    public void updatePrescriptionStatus() throws SQLException {

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(STATEMENT);
            pstmt.setObject(1, status, Types.OTHER);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        } finally {

            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

    }

}