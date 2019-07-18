package pl.akademiakodu.reporting.model.entities;

public enum Status {
    VERY_IMPORTANT("Very important"),
    IMPORTANT("Important"),
    NOT_IMPORTANT("Not important"),
    CLOSED ("Closed");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
