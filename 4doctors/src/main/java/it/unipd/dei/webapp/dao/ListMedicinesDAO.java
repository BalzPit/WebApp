package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListMedicinesDAO {
    /**
     * The SQL statement to be executed for retrieve all medicines available
     */
    private static final String MEDICINE_STATEMENT = "SELECT * FROM doctors.Farmaco";

    /**
     * The connection to the database
     */
    private final Connection con;

    /**
     * Creates a new object for checking a user into the database.
     *
     * @param con the connection to the database.
     *
     */
    public ListMedicinesDAO(final Connection con) {
        this.con = con;
    }

    /**
     * Get the list of all medicines
     *
     * @throws SQLException : if any error occurs while accessing the database.
     */
    public List<Medicine> getListMedicines() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Medicine> medicines = new ArrayList<>();


        try {
            pstmt = con.prepareStatement(MEDICINE_STATEMENT);
            result = pstmt.executeQuery();

            while (result.next()){
                medicines.add(new Medicine(result.getString("codice"), result.getString("nome"), result.getString("classe"), result.getString("azienda"), result.getString("descrizione")));
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
        return medicines;
    }
}