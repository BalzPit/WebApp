package it.unipd.dei.webapp.resource;

import com.fasterxml.jackson.core.JsonGenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;

/**
 * Represents the data about a doctor.
 */
public class Doctor extends Resource {

    /**
     * The codice fiscale of the doctor
     */
    private final String cf;

    /**
     * The name of the doctor
     */
    private final String name;

    /**
     * The surname of the doctor
     */
    private final String surname;

    /**
     * The email of the doctor
     */
    private final String email;

    /**
     * The password of the doctor
     */
    private final String password;

    /**
     * The birthday of the doctor
     */
    private final Date birthday;

    /**
     * The birthplace of the doctor
     */
    private final String birthplace;

    /**
     * The address of the doctor
     */
    private final String address;

    /**
     * The gender of the doctor
     */
    private final Gender gender;

    /**
     * The ASL code
     */
    private final String aslcode;

    /**
     * Creates a new doctor
     *
     * @param cf    the codice fiscale of the doctor
     * @param name  the name of the doctor
     * @param surname   the surname of the doctor
     * @param email the email of the doctor
     * @param password  the password of the doctor
     * @param birthday  the birthday of the doctor
     * @param birthplace    the birthplace of the doctor
     * @param address   the address of the doctor
     * @param gender    the gender of the doctor
     * @param aslcode    the ASL code of the doctor
     */

    public Doctor(final String cf, final String name, final String surname, final String email, final String password,
                   final Date birthday, final String birthplace, final String address, final Gender gender, final String aslcode) {
        this.cf = cf;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.address = address;
        this.gender = gender;
        this.aslcode = aslcode;
    }

    /**
     * Returns the codice fiscale of the doctor.
     *
     * @return the codice fiscale of the doctor.
     */
    public String getCf() {
        return cf;
    }

    /**
     * Returns the name of the doctor.
     *
     * @return the name of the doctor.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the surname of the doctor.
     *
     * @return the surname of the doctor.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Returns the email of the doctor.
     *
     * @return the email of the doctor.
     */
    public String getEmail() {
        return email;
    }


    /**
     * Returns the password of the doctor.
     *
     * @return the password of the doctor.
     */
    public String getPassword() {
        return password;
    }


    /**
     * Returns the birthday of the doctor.
     *
     * @return the birthday of the doctor.
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Returns the birthplace of the doctor.
     *
     * @return the birthplace of the doctor.
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * Returns the address of the doctor.
     *
     * @return the address of the doctor.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Returns the gender of the doctor.
     *
     * @return the gender of the doctor.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Returns the ASL code of the doctor.
     *
     * @return the ASL code of the doctor.
     */
    public String getAslcode() {
        return aslcode;
    }



    @Override
    public final void toJSON(final OutputStream out) throws IOException {

        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);

        jg.writeStartObject();

        jg.writeFieldName("doctor");

        jg.writeStartObject();

        jg.writeStringField("cf", cf);

        jg.writeStringField("name", name);

        jg.writeStringField("surname", surname);

        jg.writeStringField("email", email);

        jg.writeStringField("password", password);

        jg.writeStringField("birthday", birthday.toString());

        jg.writeStringField("birthplace", birthplace);

        jg.writeStringField("address", address);

        jg.writeStringField("gender", gender.toString());

        jg.writeStringField("aslcode", aslcode);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }
}
