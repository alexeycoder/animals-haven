package edu.alexey.animalshaven.domain.entities.animals;

import java.time.LocalDate;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.PackAnimal;

public class Mule extends PackAnimal {

	public Mule(LocalDate birthDate, String name, int loadCapacity) {
		super(birthDate, name, loadCapacity);
	}
}
