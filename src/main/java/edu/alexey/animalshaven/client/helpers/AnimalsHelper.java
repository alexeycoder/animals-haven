package edu.alexey.animalshaven.client.helpers;

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

	public static record KindId(int id, String description, Class<? extends Animal> cls) {
	}

	public static record TypeId(int id, String description, Class<? extends Animal> cls) {
	}

	public static Set<TypeId> animalTypes = Set.of(
			new TypeId(1, "Домашнее животное", PetAnimal.class),
			new TypeId(2, "Вьючное животное", PackAnimal.class));

	public static Set<KindId> animalKinds = Set.of(
			new KindId(1, "Кошка", Cat.class),
			new KindId(2, "Собака", Dog.class),
			new KindId(3, "Хомяк", Hamster.class),
			new KindId(4, "Верблюд", Camel.class),
			new KindId(5, "Лошадь", Horse.class),
			new KindId(6, "Осёл", Mule.class));

	// public static TypeId getAnimalType(Class<? extends Animal> cls) {
	// 	Objects.requireNonNull(cls);
	// 	// todo: можно добавить логирование отсутствия записи в справочнике
	// 	return animalTypes.stream().filter(t -> t.cls.isAssignableFrom(cls)).findAny().get();
	// }

	public static TypeId getAnimalType(Animal animal) {
		Objects.requireNonNull(animal);
		// todo: можно добавить логирование отсутствия записи в справочнике
		return animalTypes.stream().filter(t -> t.cls.isAssignableFrom(animal.getClass())).findAny().get();
		//return getAnimalType(animal.getClass());
	}

	// public static KindId getAnimalKind(Class<? extends Animal> cls) {
	// 	Objects.requireNonNull(cls);
	// 	// todo: можно добавить логирование отсутствия записи в справочнике
	// 	return animalKinds.stream().filter(k -> k.cls.isAssignableFrom(cls)).findAny().get();
	// }

	// может вернуть null, если передать неконкретный тип
	public static KindId getAnimalKind(Animal animal) {
		Objects.requireNonNull(animal);
		// todo: можно добавить логирование отсутствия записи в справочнике
		return animalKinds.stream().filter(k -> k.cls.isAssignableFrom(animal.getClass())).findAny().get();
		//return getAnimalKind(animal.getClass());
	}

	// private static Class<? extends Animal> getClassByKindId(int id) {
	// 	return animalKinds.entrySet().stream().filter(e -> e.getKey().id == id)
	// 			.map(Entry::getValue).findAny().orElse(null);
	// }

	// public static void main(String[] args) {
	// Animal an1 = new Dog(LocalDate.of(2023, 10, 15), "Пёс-Барбос", null);
	// Animal an2 = new Mule(LocalDate.of(2023, 05, 20), "Васька", 15);

	// var at1 = AnimalsHelper.getAnimalType(an1);
	// var ak1 = AnimalsHelper.getAnimalKind(an1);
	// System.out.println(at1.toString());
	// System.out.println(ak1.toString());

	// var at2 = AnimalsHelper.getAnimalType(an2);
	// var ak2 = AnimalsHelper.getAnimalKind(an2);
	// System.out.println(at2.toString());
	// System.out.println(ak2.toString());

	// System.out.println(getClassById(5).getSimpleName());
	// }
}
