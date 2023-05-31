package edu.alexey.animalshaven.client.viewmodels;

import java.util.List;

import edu.alexey.animalshaven.client.uielements.Menu;
import edu.alexey.animalshaven.domain.business.RepositoryRecord;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;

/**
 * Принудим реализовывать toString() у всех ViewModel,
 * поскольку это основной метод для представления в консоли.
 */
public abstract class ViewModelBase {
	public abstract String toString();

	// fab

	public static ViewModelBase of(Menu menu) {
		return new MenuViewModel(menu);
	}

	public static ViewModelBase emptySpace(Integer nLines) {
		return new ViewModelBase() {
			@Override
			public String toString() {
				return nLines == null || nLines <= 1
						? System.lineSeparator()
						: System.lineSeparator().repeat(nLines);
			}
		};
	}

	public static ViewModelBase of(RepositoryRecord<Animal> record, boolean simplified) {
		return new AnimalViewModel(record, simplified);
	}

	public static ViewModelBase of(List<RepositoryRecord<Animal>> records, boolean simplified) {
		return new AnimalsListViewModel(records, simplified);
	}
}
