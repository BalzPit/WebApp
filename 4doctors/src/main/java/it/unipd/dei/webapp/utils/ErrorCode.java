package it.unipd.dei.webapp.utils;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

public enum ErrorCode {


    DOCTOR_NOT_FOUND(100, HttpServletResponse.SC_NOT_FOUND, "Doctor not found"),
    PATIENT_NOT_FOUND(101, HttpServletResponse.SC_NOT_FOUND, "Patient not found"),
    WRONG_DATA_FORMAT(402, HttpServletResponse.SC_BAD_REQUEST, "The data is in the wrong format"),
    WRONG_JSON_FORMAT(400, HttpServletResponse.SC_BAD_REQUEST, "The input json is in the wrong format"),
    UNKNOWN_OPERATION(401, HttpServletResponse.SC_BAD_REQUEST, "Requested an unknown operation"),
    METHOD_NOT_ALLOWED(405, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method requested not implemented"),
    SERVER_ERROR(500, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");

    private final int errorCode;
    private final int httpCode;
    private final String errorMessage;

    ErrorCode(int errorCode, int httpCode, String errorMessage) {
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getHTTPCode() {
        return httpCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public JSONObject toJSON() {
        JSONObject error = new JSONObject();
        error.put("code", errorCode);
        error.put("message", errorMessage);
        JSONObject info = new JSONObject();
        info.put("error", error);
        return info;
    }
}
