package edu.alexey.animalshaven.domain.business;

import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;

public class AnimalsManager {

	private final Repository<Animal> animalsRepository;
	private final AnimalBuilder animalBuilder;

	public AnimalsManager() {
		animalBuilder = new AnimalBuilder();
		animalsRepository = new AnimalsRepositoryImpl();
		AnimalsRepositoryImpl.FillWithData(animalsRepository);
	}

	public Repository<Animal> animalsRepository() {
		return animalsRepository;
	}

	public AnimalBuilder animalBuilder() {
		return animalBuilder;
	}

}
