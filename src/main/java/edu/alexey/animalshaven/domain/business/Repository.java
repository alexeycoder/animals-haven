package edu.alexey.animalshaven.domain.business;

import java.util.List;

public interface Repository<E> {

	RepositoryRecord<E> add(E entity);

	E getById(int id);

	List<RepositoryRecord<E>> getAll();
}
