package edu.alexey.animalshaven.domain.entities.animals;

import java.time.LocalDate;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.PetAnimal;
import edu.alexey.animalshaven.domain.entities.commands.Bark;

public class Dog extends PetAnimal {

	public Dog(LocalDate birthDate, String name, String owner) {
		super(birthDate, name, owner);
		this.commands.add(new Bark(this));
	}
}
