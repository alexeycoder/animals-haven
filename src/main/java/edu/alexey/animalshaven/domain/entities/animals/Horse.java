package edu.alexey.animalshaven.domain.entities.animals;

import java.time.LocalDate;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.PackAnimal;
import edu.alexey.animalshaven.domain.entities.commands.Gallop;

public class Horse extends PackAnimal {

	protected Horse(LocalDate birthDate, String name, int loadCapacity) {
		super(birthDate, name, loadCapacity);
		
		this.commands.add(new Gallop(this));
	}

}
