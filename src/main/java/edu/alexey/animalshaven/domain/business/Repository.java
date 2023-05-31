package edu.alexey.animalshaven.domain.business;

import java.util.List;

public interface Repository<E> {

	RepositoryRecord<E> add(E entry);

	RepositoryRecord<E> getById(int id);

	List<RepositoryRecord<E>> getByName(String nameSample);

	List<RepositoryRecord<E>> getAll();

}
