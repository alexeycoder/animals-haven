package edu.alexey.animalshaven.client.helpers;

import java.util.List;
import java.util.Objects;

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

	public static record KindRef(String description, Class<? extends Animal> cls) {
	}

	public static record TypeRef(String description, Class<? extends Animal> cls) {
	}

	public static List<TypeRef> animalTypes = List.of(
			new TypeRef("Домашнее животное", PetAnimal.class),
			new TypeRef("Вьючное животное", PackAnimal.class));

	public static List<KindRef> animalKinds = List.of(
			new KindRef("Кошка", Cat.class),
			new KindRef("Собака", Dog.class),
			new KindRef("Хомяк", Hamster.class),
			new KindRef("Верблюд", Camel.class),
			new KindRef("Лошадь", Horse.class),
			new KindRef("Осёл", Mule.class));

	public static TypeRef getAnimalType(Animal animal) {
		Objects.requireNonNull(animal);
		// todo: можно добавить логирование отсутствия записи в справочнике
		return animalTypes.stream().filter(t -> t.cls.isAssignableFrom(animal.getClass())).findAny().get();
	}

	public static KindRef getAnimalKind(Animal animal) {
		Objects.requireNonNull(animal);
		// todo: можно добавить логирование отсутствия записи в справочнике
		return animalKinds.stream().filter(k -> k.cls.isAssignableFrom(animal.getClass())).findAny().get();
	}
}
