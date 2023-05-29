package edu.alexey.animalshaven.domain.entities.animals;

import java.time.LocalDate;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.PetAnimal;

public class Hamster extends PetAnimal {

	protected Hamster(LocalDate birthDate, String name, String owner) {
		super(birthDate, name, owner);
	}
}
