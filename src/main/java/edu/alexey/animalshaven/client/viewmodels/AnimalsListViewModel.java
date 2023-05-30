package edu.alexey.animalshaven.client.viewmodels;

import java.util.List;

import edu.alexey.animalshaven.domain.business.RepositoryRecord;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;

public class AnimalsListViewModel extends ViewModelBase {

	private final String strRepr;

	protected AnimalsListViewModel(List<RepositoryRecord<Animal>> records, boolean simplified) {
		StringBuilder sb = new StringBuilder();
		for (var record : records) {
			sb.append(new AnimalViewModel(record, simplified)).append(System.lineSeparator());
		}
		strRepr = sb.toString();
	}

	@Override
	public String toString() {
		return strRepr;
	}
}
