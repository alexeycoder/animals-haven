package edu.alexey.animalshaven.domain.entities.animals;

import java.time.LocalDate;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.PackAnimal;

public class Camel extends PackAnimal {

	public Camel(LocalDate birthDate, String name, int loadCapacity) {
		super(birthDate, name, loadCapacity);
	}
}
