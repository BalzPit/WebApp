package it.unipd.dei.webapp.resource;

public class Medicine {
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
}
