package it.unipd.dei.webapp.servlet;

import it.unipd.dei.webapp.resource.*;
import it.unipd.dei.webapp.rest.MedicineRestResource;
import it.unipd.dei.webapp.rest.MedicalExaminationRestResource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Manages the REST API for the different REST resources.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version 1.00
 * @since 1.00
 */
public final class RestManagerServlet extends AbstractDatabaseServlet {

    /**
     * The JSON MIME media type
     */
    private static final String JSON_MEDIA_TYPE = "application/json";

    /**
     * The JSON UTF-8 MIME media type
     */
    private static final String JSON_UTF_8_MEDIA_TYPE = "application/json; charset=utf-8";

    /**
     * The any MIME media type
     */
    private static final String ALL_MEDIA_TYPE = "*/*";

    @Override
    protected final void service(final HttpServletRequest req, final HttpServletResponse res)
            throws ServletException, IOException {

        res.setContentType(JSON_UTF_8_MEDIA_TYPE);
        final OutputStream out = res.getOutputStream();

        try {
            // if the request method and/or the MIME media type are not allowed, return.
            // Appropriate error message sent by {@code checkMethodMediaType}
            if (!checkMethodMediaType(req, res)) {
                return;
            }

            // if the requested resource was a Medicine, delegate its processing and return
            if (processMedicine(req, res)) {
                return;
            }
            // if the requested resource was a Medical Examination, delegate its processing and return
            if (processMedicalExamination(req, res)) {
                return;
            }

            // if none of the above process methods succeeds, it means an unknow resource has been requested
            final Message m = new Message("Unknown resource requested.", "E4A6",
                    String.format("Requested resource is %s.", req.getRequestURI()));
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            m.toJSON(out);
        } finally {
            // ensure to always flush and close the output stream
            out.flush();
            out.close();
        }
    }

    /**
     * Checks that the request method and MIME media type are allowed.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request method and the MIME type are allowed; {@code false} otherwise.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */
    private boolean checkMethodMediaType(final HttpServletRequest req, final HttpServletResponse res)
            throws IOException {

        final String method = req.getMethod();
        final String contentType = req.getHeader("Content-Type");
        final String accept = req.getHeader("Accept");
        final OutputStream out = res.getOutputStream();

        Message m = null;

        if (accept == null) {
            m = new Message("Output media type not specified.", "E4A1", "Accept request header missing.");
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            m.toJSON(out);
            return false;
        }

        if (!accept.contains(JSON_MEDIA_TYPE) && !accept.equals(ALL_MEDIA_TYPE)) {
            m = new Message("Unsupported output media type. Resources are represented only in application/json.",
                    "E4A2", String.format("Requested representation is %s.", accept));
            res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            m.toJSON(out);
            return false;
        }

        switch (method) {
            case "GET":
            case "DELETE":
                // nothing to do
                break;

            case "POST":
            case "PUT":
                if (contentType == null) {
                    m = new Message("Input media type not specified.", "E4A3", "Content-Type request header missing.");
                    res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    m.toJSON(out);
                    return false;
                }

                if (!contentType.contains(JSON_MEDIA_TYPE)) {
                    m = new Message("Unsupported input media type. Resources are represented only in application/json.",
                            "E4A4", String.format("Submitted representation is %s.", contentType));
                    res.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                    m.toJSON(out);
                    return false;
                }

                break;
            default:
                m = new Message("Unsupported operation.",
                        "E4A5", String.format("Requested operation %s.", method));
                res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                m.toJSON(out);
                return false;
        }

        return true;
    }


    /**
     * Checks whether the request if for an {@link Medicine} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code Employee}; {@code false} otherwise.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */
    private boolean processMedicine(HttpServletRequest req, HttpServletResponse res) throws IOException {

        final String method = req.getMethod();
        final OutputStream out = res.getOutputStream();

        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not an employee
        if(path.lastIndexOf("rest/medicine") <= 0) {
            return false;
        }

        try {
            // strip everyhing until after the /medicine
            path = path.substring(path.lastIndexOf("medicine") + 8);

            // the request URI is: /employee
            // if method GET, list employees
            // if method POST, create employee
            if (path.length() == 0 || path.equals("/")) {

                switch (method) {
                    case "GET":
                        new MedicineRestResource(req, res, getDataSource().getConnection()).listMedicines();
                        break;
                    case "POST":
                        new MedicineRestResource(req, res, getDataSource().getConnection()).addMedicine();
                        break;
                    default:
                        m = new Message("Unsupported operation for URI /medicine.",
                                "E4A5", String.format("Requested operation %s.", method));
                        res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        m.toJSON(res.getOutputStream());
                        break;
                }
            }

        } catch(Throwable t) {
            m = new Message("Unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }

        return true;

    }

     
    /**
     * Checks whether the request if for an {@link MedicalExamination} resource and, in case, processes it.
     *
     * @param req the HTTP request.
     * @param res the HTTP response.
     * @return {@code true} if the request was for an {@code Employee}; {@code false} otherwise.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */
    private boolean processMedicalExamination(HttpServletRequest req, HttpServletResponse res) throws IOException {

        final String method = req.getMethod();
        final OutputStream out = res.getOutputStream();

        String path = req.getRequestURI();
        Message m = null;

        // the requested resource was not an employee
        if(path.lastIndexOf("rest/medicalExamination") <= 0) {
            return false;
        }

        try {
            // strip everyhing until after the /medicalExamination
            path = path.substring(path.lastIndexOf("medicalExamination") + 18);

            // the request URI is: /employee
            // if method GET, list employees
            // if method POST, create employee
            if (path.length() == 0 || path.equals("/")) {

                switch (method) {
                    case "POST":
                        new MedicalExaminationRestResource(req, res, getDataSource().getConnection()).createMedicalExamination();
                        break;
                    default:
                        m = new Message("Unsupported operation for URI /medicalExamination.",
                                "E4A5", String.format("Requested operation %s.", method));
                        res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                        m.toJSON(res.getOutputStream());
                        break;
                }
            } else {
                // the request URI is: /medicalExamination/patient/{patient_cf}
                if (path.contains("patient")) {
                    path = path.substring(path.lastIndexOf("patient") + 7);

                    if (path.length() == 0 || path.equals("/")) {
                        m = new Message("Wrong format for URI /medicalExamination/patient/{patient_cf}: no {patient_cf} specified.",
                                "E4A7", String.format("Requesed URI: %s.", req.getRequestURI()));
                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        m.toJSON(res.getOutputStream());
                    } else {
                        switch (method) {
                            case "GET":
                                new MedicalExaminationRestResource(req, res, getDataSource().getConnection()).searchMedicalExaminationByPatient();
                                break;
                            default:
                                m = new Message("Unsupported operation for URI /medicalExamination/patient/{patient_cf}.", "E4A5",
                                        String.format("Requested operation %s.", method));
                                res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                                m.toJSON(res.getOutputStream());
                                break;
                        }
                    }
                } else {
                    // the request URI is: /medicalExamination/{doctor_cf}/{date}/{time}
                    switch (method) {
                        case "GET":
                            new MedicalExaminationRestResource(req, res, getDataSource().getConnection()).readMedicalExamination();
                            break;
                        case "PUT":
                            new MedicalExaminationRestResource(req, res, getDataSource().getConnection()).updateMedicalExaminationOutcome();
                            break;
                        case "DELETE":
                            new MedicalExaminationRestResource(req, res, getDataSource().getConnection()).deleteMedicalExamination();
                            break;
                        default:
                            m = new Message("Unsupported operation for URI /medicalExamination/{doctor_cf}/{date}/{time}.",
                                    "E4A5", String.format("Requested operation %s.", method));
                            res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                            m.toJSON(res.getOutputStream());
                    }
                }
            }
        } catch(Throwable t) {
            m = new Message("Unexpected error.", "E5A1", t.getMessage());
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            m.toJSON(res.getOutputStream());
        }

        return true;

    }
}
