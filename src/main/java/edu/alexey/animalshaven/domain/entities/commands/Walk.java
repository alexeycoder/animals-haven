package edu.alexey.animalshaven.domain.entities.commands;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.PetAnimal;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.CommandBase;

public class Walk extends CommandBase {

	public Walk(PetAnimal petAnimal) {
		super(() -> {
			if (petAnimal.hasOwner()){
				System.out.printf("%s: гуляет с хозяином %s.\n", petAnimal.getName(), petAnimal.getOwner());
			} else {
				System.out.printf("%s: слоняется по улицам.\n", petAnimal.getName());
			}
		});
	}

	@Override
	public String description() {
		return "Гулять";
	}
}
