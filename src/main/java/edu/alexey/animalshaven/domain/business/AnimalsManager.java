package edu.alexey.animalshaven.domain.business;

import java.time.LocalDate;

import edu.alexey.animalshaven.domain.entities.animals.Camel;
import edu.alexey.animalshaven.domain.entities.animals.Cat;
import edu.alexey.animalshaven.domain.entities.animals.Dog;
import edu.alexey.animalshaven.domain.entities.animals.Hamster;
import edu.alexey.animalshaven.domain.entities.animals.Horse;
import edu.alexey.animalshaven.domain.entities.animals.Mule;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.CommandInjector;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.Command;

public class AnimalsManager {

	private final Repository<Animal> animalsRepository;

	public AnimalsManager() {
		animalsRepository = new AnimalsRepositoryImpl();
	}

	public Repository<Animal> animalsRepository() {
		return animalsRepository;
	}

	public boolean learnCommand(Animal animal, Command command) {
		return CommandInjector.learn(animal, command);
	}

	public void FillWithData() {
		
		animalsRepository.add(new Cat(LocalDate.of(2020, 9, 2), "Анфиса", "Виолетта Михайловна"));
		animalsRepository.add(new Cat(LocalDate.of(2022, 1, 16), "Мурка", "Юрий Дмитриевич"));
		animalsRepository.add(new Dog(LocalDate.of(2019, 10, 15), "Барбос", null));
		animalsRepository.add(new Dog(LocalDate.of(2015, 8, 15), "Рекс", "Рихард Мозер"));
		animalsRepository.add(new Hamster(LocalDate.of(2019, 8, 20), "Жужжа", "Василий Хомяков"));
		
		animalsRepository.add(new Horse(LocalDate.of(2022, 2, 11), "Чайка", 150));
		animalsRepository.add(new Horse(LocalDate.of(2021, 4, 27), "Заря", 120));
		animalsRepository.add(new Camel(LocalDate.of(2016, 11, 4), "Валет", 130));
		animalsRepository.add(new Camel(LocalDate.of(2018, 10, 19), "Генри", 110));
		animalsRepository.add(new Mule(LocalDate.of(2023, 5, 20), "Отто", 80));
	}
}
