package edu.alexey.animalshaven.domain.entities.commands;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.CommandBase;

public class Sleep extends CommandBase {

	public Sleep(Animal animal) {
		super(() -> {
			System.out.printf("%s: Хррр... спит.\n", animal.getName());
		});
	}

	@Override
	public final String description() {
		return "Спать";
	}
}
