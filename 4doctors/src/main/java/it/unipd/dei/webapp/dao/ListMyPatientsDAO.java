package it.unipd.dei.webapp.dao;

import it.unipd.dei.webapp.resource.Patient;
import it.unipd.dei.webapp.resource.Gender;


import java.sql.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListMyPatientsDAO{

    private static final String ListMyPatients_STATEMENT = "SELECT * FROM doctors.Segue INNER JOIN doctors.Paziente ON (paziente = cf) WHERE medico = ?;";

    private final Connection con;

    private final String cf;


    /**
     * Creates a new object for checking a user into the database.
     *
     * @param con
     *            the connection to the database.
     */
    public ListMyPatientsDAO(final Connection con, final String cf) {
        this.con = con;
        this.cf = cf;
    }

    /**
     * Get the list of the prescriptions of a patient
     *
     * @throws SQLException
     *             if any error occurs while accessing the database.
     */
    public List<Patient> listMyPatients() throws SQLException {

        PreparedStatement pstmt = null;
        ResultSet result = null;
        List<Patient> patients = new ArrayList<>();


        try {

            String name;
            String surname;
            String email;
            String password;
            Gender gender;
            Date birthday;
            String birthplace;
            String city;
            String doctor;
            String patient;



            pstmt = con.prepareStatement(ListMyPatients_STATEMENT);
            pstmt.setString(1, cf);
            result = pstmt.executeQuery();




            while (result.next()){
                patient = result.getString("paziente");
                doctor = result.getString("medico");
                name = result.getString("nome");
                surname = result.getString("cognome");
                email= result.getString("email");
                password = result.getString("password");
                gender = Gender.valueOf(result.getString("sesso"));
                birthday = result.getDate("datanascita");
                birthplace = result.getString("luogonascita");
                city = result.getString("indirizzoresidenza");


                patients.add(new Patient(patient, name, surname, email, password, (java.sql.Date) birthday, birthplace, city, gender));
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
        return patients;
    }

    public void addMyPatient(final String doctor_cf, final String patient_cf) throws SQLException {
        /**
         * The SQL statement to be executed
         */
        final String STATEMENT = "INSERT INTO doctors.Segue (medico, paziente, attivo) VALUES (?,?, True)";

        PreparedStatement pstmt = null;



        try {
            pstmt = con.prepareStatement(STATEMENT);

            pstmt.setObject(1, doctor_cf);
            pstmt.setString(2, patient_cf);

            pstmt.execute();


        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            con.close();
        }

    }
}