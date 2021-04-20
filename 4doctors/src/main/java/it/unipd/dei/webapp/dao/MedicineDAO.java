package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Medicine;
import it.unipd.dei.webapp.resource.Prescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class containing all the useful operation for the resource Medicine in the database
 */
public class MedicineDAO {

    /**
     * The connection to the database
     */
    Connection con = null;

    /**
     * Create an object containing all the useful operation for the resource Medicine in the database
     *
     * @param con The connection to the database
     */
    public MedicineDAO(final Connection con) {
        this.con = con;
    }

    /**
     * Get the list of all medicines
     *
     * @throws SQLException : if any error occurs while accessing the database.
     */
    public List<Medicine> getListMedicines() throws SQLException {

        final String MEDICINE_STATEMENT = "SELECT * FROM doctors.Farmaco";

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

    /**
     * Insert new medicine in the database
     *
     * @throws SQLException : if any error occurs while accessing the database.
     */
    public Boolean addMedicine(Medicine medicine) throws SQLException {

        final String MEDICINE_STATEMENT = "INSERT INTO doctors.farmaco VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pstmt = null;
        String code = medicine.getCode();
        String name = medicine.getName();
        String m_class = medicine.getMedicine_class();
        String producer = medicine.getProducer();
        String description = medicine.getDescription();
        int index = 1;

        try {
            pstmt = con.prepareStatement(MEDICINE_STATEMENT);
            pstmt.setString(index++, code);
            pstmt.setString(index++, name);
            pstmt.setObject(index++, m_class, Types.OTHER);
            pstmt.setString(index++, producer);
            pstmt.setString(index, description);
            pstmt.execute();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }
        return true;  //if no exeptions has been thrown
    }

}
