package uz.app.entity.enums;

import lombok.Getter;

@Getter
public enum Measure {
    L("Liter"),
    KG("Kilogram"),
    M("Meter"),
    SM("Centimeter"),
    GR("Gram"),
    IMMEASURE("Immeasure");

    private final String name;

    Measure(String name) {
        this.name = name;
    }
}
