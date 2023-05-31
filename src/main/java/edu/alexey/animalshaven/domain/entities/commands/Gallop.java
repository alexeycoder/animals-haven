package edu.alexey.animalshaven.domain.entities.commands;

import edu.alexey.animalshaven.domain.entities.animals.Horse;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.CommandBase;

public class Gallop extends CommandBase {

	public Gallop(Horse horse) {
		super(() -> {
			System.out.printf("%s: Скачет галопом.\n", horse.getName());
		});
	}

	@Override
	public String description() {
		return "Скакать галопом";
	}
}
