package edu.alexey.animalshaven.client.helpers;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;

import edu.alexey.animalshaven.domain.entities.animals.Camel;
import edu.alexey.animalshaven.domain.entities.animals.Cat;
import edu.alexey.animalshaven.domain.entities.animals.Dog;
import edu.alexey.animalshaven.domain.entities.animals.Hamster;
import edu.alexey.animalshaven.domain.entities.animals.Horse;
import edu.alexey.animalshaven.domain.entities.animals.Mule;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.PackAnimal;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.PetAnimal;

public class AnimalsHelper {

	public static record TypeItem(int id, String description) {
	}

	private static Map<Class<? extends Animal>, TypeItem> animalTypes = Map.of(
			PetAnimal.class, new TypeItem(1, "Домашнее животное"),
			PackAnimal.class, new TypeItem(2, "Вьючное животное"));

	private static Map<Class<? extends Animal>, TypeItem> animalKinds = Map.of(
			Cat.class, new TypeItem(1, "Кошка"),
			Dog.class, new TypeItem(2, "Собака"),
			Hamster.class, new TypeItem(3, "Хомяк"),
			Camel.class, new TypeItem(4, "Верблюд"),
			Horse.class, new TypeItem(5, "Лошадь"),
			Mule.class, new TypeItem(6, "Осёл"));

	public static TypeItem getAnimalType(Animal animal) {
		Objects.requireNonNull(animal);

		// todo: можно добавить логирование отсутствия записи в справочнике
		return animalTypes.entrySet().stream()
				.filter(e -> e.getKey().isAssignableFrom(animal.getClass()))
				.map(Entry::getValue).findAny().get();
	}

	public static TypeItem getAnimalKind(Animal animal) {
		Objects.requireNonNull(animal);

		// todo: можно добавить логирование отсутствия записи в справочнике
		return animalKinds.entrySet().stream()
				.filter(e -> e.getKey().isAssignableFrom(animal.getClass()))
				.map(Entry::getValue).findAny().get();
	}

	private static Class<? extends Animal> getClassById(int id) {
		return animalKinds.entrySet().stream().filter(e -> e.getValue().id == id)
				.map(Entry::getKey).findAny().orElse(null);
	}

	public static void main(String[] args) {
		Animal an1 = new Dog(LocalDate.of(2023, 10, 15), "Пёс-Барбос", null);
		Animal an2 = new Mule(LocalDate.of(2023, 05, 20), "Васька", 15);

		var at1 = AnimalsHelper.getAnimalType(an1);
		var ak1 = AnimalsHelper.getAnimalKind(an1);
		System.out.println(at1.toString());
		System.out.println(ak1.toString());

		var at2 = AnimalsHelper.getAnimalType(an2);
		var ak2 = AnimalsHelper.getAnimalKind(an2);
		System.out.println(at2.toString());
		System.out.println(ak2.toString());

		System.out.println(getClassById(5).getSimpleName());
	}
}
