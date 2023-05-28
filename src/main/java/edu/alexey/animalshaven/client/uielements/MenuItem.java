package edu.alexey.animalshaven.client.uielements;

import java.util.function.Function;

public record MenuItem(int order, String name, Function<?, ?> handler) {
}
