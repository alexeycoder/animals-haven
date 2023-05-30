package edu.alexey.animalshaven.domain.entities.animals.abstractions;

import edu.alexey.animalshaven.domain.entities.commands.abstractions.Command;

public class CommandInjector {
	public static boolean learn(Animal animal, Command command) {
		if (animal == null || command == null) {
			return false;
		}

		// При необходимости здесь может быть дополнительная логика
		// проверки валидности команды для конкретного типа животного и т.п.

		animal.commands.add(command);
		return true;
	}
}
