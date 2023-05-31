package edu.alexey.animalshaven.domain.entities.commands;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.PackAnimal;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.CommandBase;

public class Graze extends CommandBase {

	public Graze(PackAnimal packAnimal) {
		super(() -> {
			System.out.printf("%s: пасётся на лугу.\n", packAnimal.getName());
		});
	}

	@Override
	public String description() {
		return "Пастись";
	}
}
