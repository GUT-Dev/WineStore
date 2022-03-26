package com.winestore.enums;

public enum WineType {
    WHITE, RED, ROSE, SPARKLING, FORTIFIED
}

class ConvertEnum {
    static String getDisplayName(WineType wineType) {
        String result;
        switch (wineType) {
            case RED -> result = "червоне";
            case WHITE -> result = "біле";
            case ROSE -> result = "рожеве";
            case SPARKLING -> result = "ігристе";
            case FORTIFIED -> result = "кріплене";
            default -> throw new IllegalStateException("Unexpected value: " + wineType);
        }
        return result;
    }
}
