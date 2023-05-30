package edu.alexey.animalshaven.domain.business;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.CommandInjector;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.Command;

public class AnimalsManager {

	private final Repository<Animal> animalsRepository;

	public AnimalsManager() {
		animalsRepository = new AnimalsRepositoryImpl();
		AnimalsRepositoryImpl.FillWithData(animalsRepository);
	}

	public Repository<Animal> animalsRepository() {
		return animalsRepository;
	}

	public boolean learnCommand(Animal animal, Command command) {
		return CommandInjector.learn(animal, command);
	}

}
