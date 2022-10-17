package model;

public enum ComplexityType {

    BAJA,
    MEDIA,
    ALTA;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
