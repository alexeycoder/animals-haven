package edu.alexey.animalshaven.domain.entities.commands;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.CommandBase;

public class Eat extends CommandBase {

	public Eat(Animal animal) {
		super(() -> {
			System.out.printf("%s: Ням-ням... ест.", animal.getName());
		});
	}

	@Override
	public final String description() {
		return "Есть еду";
	}
}
