package de.holisticon.java22.engine;

public record Car<T extends Engine>(String name, String color, T engine) {
}

