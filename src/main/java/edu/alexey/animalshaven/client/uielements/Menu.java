package edu.alexey.animalshaven.client.uielements;

import java.util.Map;
import java.util.Set;

public record Menu(String header, Map<Set<String>, MenuItem> items) {
}
