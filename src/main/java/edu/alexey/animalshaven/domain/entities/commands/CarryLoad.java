package edu.alexey.animalshaven.domain.entities.commands;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.PackAnimal;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.CommandBase;

public class CarryLoad extends CommandBase {

	private final String description;

	public CarryLoad(PackAnimal packAnimal, int weight) {
		super(() -> {
			if (weight > packAnimal.getLoadCapacity()) {
				System.out.printf("%s: пытается тащить груз %d кг., но не получается.\n",
						packAnimal.getName(),
						weight);
			} else {
				System.out.printf("%s: тащит груз %d кг.\n", packAnimal.getName(), weight);
			}
		});

		this.description = "Тащить (заряжено груза " + weight + "кг.)";
	}

	@Override
	public String description() {
		return description;
	}
}
