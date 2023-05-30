package edu.alexey.animalshaven.domain.business;

import java.security.InvalidParameterException;
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

	private static final int BASE_ID = 1;
	private static final ArrayList<Animal> list = new ArrayList<>();

	private static int toIndex(int id) {
		return id - BASE_ID;
	}

	private static int toId(int index) {
		return index + BASE_ID;
	}

	private int lastId() {
		return toId(list.size() - 1);
	}

	@Override
	public RepositoryRecord<Animal> add(Animal entry) {
		Objects.requireNonNull(entry);

		int index = list.indexOf(entry);
		if (index != -1) {
			return new RepositoryRecord<Animal>(toId(index), entry);
		}

		list.add(entry);
		return new RepositoryRecord<Animal>(lastId(), entry);
	}

	@Override
	public RepositoryRecord<Animal> getById(int id) {
		if (id > lastId() || id < BASE_ID) {
			return null;
		}
		var entry = list.get(toIndex(id));
		return entry != null ? new RepositoryRecord<Animal>(id, entry) : null;
	}

	@Override
	public List<RepositoryRecord<Animal>> getByName(String nameSample) {
		if (nameSample == null) {
			throw new NullPointerException("nameSample");
		}
		if (nameSample.isBlank()) {
			throw new InvalidParameterException("nameSample");
		}

		final String nameLc = nameSample;
		var foundEntries = getAll().stream()
		.filter(r -> r.entity().getName().toLowerCase().contains(nameLc)).toList();
		return foundEntries;
	}

	@Override
	public List<RepositoryRecord<Animal>> getAll() {
		if (list.isEmpty()) {
			return List.of();
		}
		return IntStream.range(BASE_ID, lastId() + 1)
				.mapToObj(id -> new RepositoryRecord<Animal>(id, list.get(toIndex(id)))).toList();
	}

	public static void FillWithData(Repository<Animal> repository) {

		repository.add(new Dog(LocalDate.of(2023, 10, 15), "Пёс-Барбос", null));
		repository.add(new Dog(LocalDate.of(2022, 8, 15), "Сабака-Бабака", null));
		repository.add(new Cat(LocalDate.of(2022, 9, 2), "Борис", "Виолетта Михайловна"));
		repository.add(new Mule(LocalDate.of(2023, 5, 20), "Васька", 15));
	}

}
