package edu.alexey.animalshaven.domain.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

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

}
