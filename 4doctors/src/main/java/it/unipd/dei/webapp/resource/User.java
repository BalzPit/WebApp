package it.unipd.dei.webapp.resource;

/**
 * Represents the data about a user registered in the webapp.
 */
public class User {

    /**
     * The codice fiscale of the user
     */
    private final String cf;

    /**
     * The password of the user
     */
    private final String password;

    /**
     * The role that a user can assume.
     * A user can be a patient or a medic
     */
    private final String role;

    /**
     * Create a new user
     *
     * @param cf the codice fiscale of the user
     * @param password the password of the user
     * @param role the role a user can assume
     */
    public User(final String cf, final String password, final String role){
        this.cf = cf;
        this.password = password;
        this.role = role;
    }

    /**
     * Returns the codice fiscale of the user
     * @return the codice fiscale of the user
     */
    public final String getCf() {
        return cf;
    }

    /**
     * Returns the password of the user
     * @return the password of the user
     */
    public final String getPassword() {
        return password;
    }

    /**
     * Returns the role of the user
     * @return the role of the user
     */
    public final String getRole() {
        return role;
    }
}
