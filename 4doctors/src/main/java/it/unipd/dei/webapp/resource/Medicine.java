package it.unipd.dei.webapp.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Medicine extends Resource {
    private final String code;
    private final String name;
    private final String medicine_class;
    private final String producer;
    private final String description;

    public Medicine(final String code, final String name, final String medicine_class, final String producer, final String description){

        this.code = code;
        this.name = name;
        this.medicine_class = medicine_class;
        this.producer = producer;
        this.description = description;

    }

    public final String getProducer() {
        return producer;
    }

    public final String getMedicine_class() {
        return medicine_class;
    }

    public final String getCode() {
        return code;
    }

    public final String getDescription() {
        return description;
    }

    public final String getName() {
        return name;
    }

    @Override
    public final void toJSON(OutputStream out) throws IOException {
        final JsonGenerator jg = JSON_FACTORY.createGenerator(out);
        jg.writeStartObject();

        jg.writeFieldName("medicine");

        jg.writeStartObject();

        jg.writeStringField("code", code);

        jg.writeStringField("name", name);

        jg.writeStringField("medicine_class", medicine_class);

        jg.writeStringField("producer", producer);

        jg.writeStringField("description", description);

        jg.writeEndObject();

        jg.writeEndObject();

        jg.flush();
    }

    /**
     * Creates a {@code Employee} from its JSON representation.
     *
     * @param in the input stream containing the JSON document.
     *
     * @return the {@code Employee} created from the JSON representation.
     *
     * @throws IOException if something goes wrong while parsing.
     */
    public static Medicine fromJSON(final InputStream in) throws IOException {

        // the fields read from JSON
        String jCode = null;
        String jName = null;
        String jMedicine_class = null;
        String jProducer = null;
        String jDescription = null;

        final JsonParser jp = JSON_FACTORY.createParser(in);

        // while we are not on the start of an element or the element is not
        // a token element, advance to the next element (if any)
        while (jp.getCurrentToken() != JsonToken.FIELD_NAME || "medicine".equals(jp.getCurrentName()) == false) {

            // there are no more events
            if (jp.nextToken() == null) {
                throw new IOException("Unable to parse JSON: no employee object found.");
            }
        }

        while (jp.nextToken() != JsonToken.END_OBJECT) {

            if (jp.getCurrentToken() == JsonToken.FIELD_NAME) {

                switch (jp.getCurrentName()) {
                    case "code":
                        jp.nextToken();
                        jCode = jp.getText();
                        break;
                    case "name":
                        jp.nextToken();
                        jName = jp.getText();
                        break;
                    case "medicine_class":
                        jp.nextToken();
                        jMedicine_class = jp.getText();
                        break;
                    case "producer":
                        jp.nextToken();
                        jProducer = jp.getText();
                        break;
                    case "description":
                        jp.nextToken();
                        jDescription = jp.getText();
                        break;
                }
            }
        }

        return new Medicine(jCode, jName, jMedicine_class, jProducer, jDescription);
    }

}
