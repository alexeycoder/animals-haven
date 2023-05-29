package edu.alexey.animalshaven.domain.entities.commands;

import edu.alexey.animalshaven.domain.entities.animals.Dog;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.CommandBase;

public class Bark extends CommandBase {

	public Bark(Dog dog) {
		super(() -> {
			System.out.printf("%s: Гав-гав!\n", dog.getName());
		});
	}

	@Override
	public final String description() {
		return "Голос - гавкать";
	}
}
