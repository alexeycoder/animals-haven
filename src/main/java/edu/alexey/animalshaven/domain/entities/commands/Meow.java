package edu.alexey.animalshaven.domain.entities.commands;

import edu.alexey.animalshaven.domain.entities.animals.Cat;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.CommandBase;

public class Meow extends CommandBase {

	public Meow(Cat cat) {
		super(() -> {
			System.out.printf("%s: Гав-гав!\n", cat.getName());
		});
	}

	@Override
	public final String description() {
		return "Мяукать (стандартное гавканье)";
	}
}
