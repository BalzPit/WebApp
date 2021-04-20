package it.unipd.dei.webapp.utils;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

/**
 * Enum that contains all the possible error in the webapp
 */
public enum ErrorCode {

    // List of all the errors
    DOCTOR_NOT_FOUND(100, HttpServletResponse.SC_NOT_FOUND, "Doctor not found"),
    PATIENT_NOT_FOUND(101, HttpServletResponse.SC_NOT_FOUND, "Patient not found"),
    WRONG_DATA_FORMAT(402, HttpServletResponse.SC_BAD_REQUEST, "The data is in the wrong format"),
    WRONG_JSON_FORMAT(400, HttpServletResponse.SC_BAD_REQUEST, "The input json is in the wrong format"),
    UNKNOWN_OPERATION(401, HttpServletResponse.SC_BAD_REQUEST, "Requested an unknown operation"),
    METHOD_NOT_ALLOWED(405, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method requested not implemented"),
    SERVER_ERROR(500, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");

    /**
     * The error code in the webapp
     */
    private final int errorCode;

    /**
     * The http error code
     */
    private final int httpCode;

    /**
     * The message of the error
     */
    private final String errorMessage;

    /**
     * Create a new ErrorCode Object
     */
    ErrorCode(int errorCode, int httpCode, String errorMessage) {
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Get the error code
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Get the http error
     *
     * @return the http code
     */
    public int getHTTPCode() {
        return httpCode;
    }

    /**
     * Get the message of the error
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Convert an ErrorCode into a JSONObject
     *
     * @return a JSONObject containing an ErrorCode
     */
    public JSONObject toJSON() {
        JSONObject error = new JSONObject();
        error.put("code", errorCode);
        error.put("message", errorMessage);
        JSONObject info = new JSONObject();
        info.put("error", error);
        return info;
    }
}
