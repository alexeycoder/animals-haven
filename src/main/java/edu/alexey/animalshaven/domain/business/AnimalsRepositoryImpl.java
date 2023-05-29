package edu.alexey.animalshaven.domain.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import edu.alexey.animalshaven.domain.entities.animals.Cat;
import edu.alexey.animalshaven.domain.entities.animals.Dog;
import edu.alexey.animalshaven.domain.entities.animals.Mule;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;

public class AnimalsRepositoryImpl implements Repository<Animal> {

	private static final ArrayList<Animal> list = new ArrayList<>();

	@Override
	public RepositoryRecord<Animal> add(Animal entity) {
		Objects.requireNonNull(entity);

		int idx = list.indexOf(entity);
		if (idx != -1) {
			return new RepositoryRecord<Animal>(idx, entity);
		}

		list.add(entity);
		return new RepositoryRecord<Animal>(list.size() - 1, entity);
	}

	@Override
	public Animal getById(int id) {
		if (id >= list.size() || id < 0) {
			return null;
		}

		return list.get(id);
	}

	@Override
	public List<RepositoryRecord<Animal>> getAll() {
		if (list.isEmpty()) {
			return List.of();
		}
		return IntStream.range(0, list.size() - 1)
				.mapToObj(i -> new RepositoryRecord<Animal>(i, list.get(i))).toList();
	}

	public static void FillWithData(Repository<Animal> repository) {

		repository.add(new Dog(LocalDate.of(2023, 10, 15), "Пёс-Барбос", null));
		repository.add(new Dog(LocalDate.of(2022, 8, 15), "Сабака-Бабака", null));
		repository.add(new Cat(LocalDate.of(2022, 9, 2), "Борис", "Виолетта Михайловна"));
		repository.add(new Mule(LocalDate.of(2023, 5, 20), "Васька", 15));
	}

}
