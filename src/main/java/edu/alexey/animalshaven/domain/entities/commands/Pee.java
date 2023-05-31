package edu.alexey.animalshaven.domain.entities.commands;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.CommandBase;

public class Pee extends CommandBase {

	public Pee(Animal animal) {
		super(() -> {
			System.out.printf("%s: ходит по нужде...\n", animal.getName());
		});
	}

	@Override
	public String description() {
		return "Туалет";
	}
}
