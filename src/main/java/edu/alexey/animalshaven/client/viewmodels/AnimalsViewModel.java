package edu.alexey.animalshaven.client.viewmodels;

import java.util.List;

import edu.alexey.animalshaven.domain.business.RepositoryRecord;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;

public class AnimalsViewModel extends ViewModelBase {

	private final String strRepr;

	protected AnimalsViewModel(List<RepositoryRecord<Animal>> records) {
		StringBuilder sb = new StringBuilder();
		for (var record : records) {
			sb.append(new AnimalViewModel(record))
					.append(System.lineSeparator())
					.append(System.lineSeparator());
		}
		strRepr = sb.toString();
	}

	@Override
	public String toString() {
		return strRepr;
	}
}
