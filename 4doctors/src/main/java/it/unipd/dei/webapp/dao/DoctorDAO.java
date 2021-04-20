package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Doctor;
import it.unipd.dei.webapp.resource.Gender;
import it.unipd.dei.webapp.utils.DataSourceProvider;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing all the useful operations to handle doctor resource in the database
 *
 * @author Pietro Balzan
 */
public class DoctorDAO {

    /**
     * Stores a new doctor into the database
     *
     * @param doctor the doctor to store in the database
     * @throws SQLException
     *             if any error occurs while storing the doctor.
     */
    public static void createDoctor(Doctor doctor) throws SQLException, NamingException {

        final String STATEMENT = "INSERT INTO doctors.Medico (cf, nome, cognome, email, password, sesso, datanascita, luogonascita, " +
                "CodiceASL, indirizzoresidenza) VALUES (?, ?, ?, ?, MD5(?), ?, ?, ?, ?, ?)";

        try (Connection con  = DataSourceProvider.getDataSource().getConnection()) {

            PreparedStatement pstmt = con.prepareStatement(STATEMENT);

            pstmt.setString(1, doctor.getCf());
            pstmt.setString(2, doctor.getName());
            pstmt.setString(3, doctor.getSurname());
            pstmt.setString(4, doctor.getEmail());
            pstmt.setString(5, doctor.getPassword());
            pstmt.setObject(6, doctor.getGender(), Types.OTHER);
            pstmt.setDate(7, doctor.getBirthday());
            pstmt.setString(8, doctor.getBirthplace());
            pstmt.setString(9, doctor.getAslcode());
            pstmt.setString(10, doctor.getAddress());

            pstmt.execute();
        }
    }


    /**
     * Get the Doctor specified by the cf from the database
     *
     * @param cf
     * @return
     * @throws SQLException
     * @throws NamingException
     */
    public static Doctor searchDoctorByCF(String cf) throws SQLException, NamingException {
        final String SINGLE_DOCTOR_STATEMENT = "SELECT * FROM doctors.Medico Where cf = ?";

        PreparedStatement pstmt_doctor = null;
        Connection con = null;
        ResultSet rs_doctor = null;

        final Doctor doc;

        try {

            con = DataSourceProvider.getDataSource().getConnection();

            pstmt_doctor = con.prepareStatement(SINGLE_DOCTOR_STATEMENT);
            pstmt_doctor.setString(1, cf);

            rs_doctor = pstmt_doctor.executeQuery();

            //select the only doctor if any
            rs_doctor.next();

            doc = new Doctor(
                    rs_doctor.getString("cf"),
                    rs_doctor.getString("nome"),
                    rs_doctor.getString("cognome"),
                    rs_doctor.getString("email"),
                    rs_doctor.getString("password"),
                    rs_doctor.getDate("datanascita"),
                    rs_doctor.getString("luogonascita"),
                    rs_doctor.getString("indirizzoresidenza"),
                    Gender.valueOf(rs_doctor.getString("sesso")),
                    rs_doctor.getString("CodiceASL")
            );

        } finally {
            if (pstmt_doctor != null) {
                pstmt_doctor.close();
            }
            if(rs_doctor != null){
                rs_doctor.close();
            }

            con.close();
        }

        return doc;
    }



    /**
     * get a list of the currently active doctors of a given patient from the database
     *
     * @param patient_cf
     *              the cf of the patient of whom we want to get the doctors' list
     */
    public static List<Doctor> searchActiveDoctorsByPatientCF(String patient_cf) throws SQLException, NamingException{
        final String DOCTOR_LIST_STATEMENT = "SELECT medico FROM doctors.Segue WHERE paziente = ? AND attivo = '1'";

        PreparedStatement pstmt_list = null;
        Connection con = null;
        ResultSet rs_list = null;

        final List<Doctor> doctors = new ArrayList<Doctor>();

        try {

            con = DataSourceProvider.getDataSource().getConnection();
            pstmt_list = con.prepareStatement(DOCTOR_LIST_STATEMENT);

            pstmt_list.setString(1, patient_cf);

            rs_list = pstmt_list.executeQuery();

            while (rs_list.next()){
                //get the doctor from te server
                Doctor doc = DoctorDAO.searchDoctorByCF(rs_list.getString("medico"));

                doctors.add(doc);
            }

        } finally {
            if (pstmt_list != null) {
                pstmt_list.close();
            }
            if(rs_list != null){
                rs_list.close();
            }

            con.close();
        }

        return doctors;
    }
}
