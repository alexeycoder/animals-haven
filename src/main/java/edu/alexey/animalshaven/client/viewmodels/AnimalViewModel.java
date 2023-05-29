package edu.alexey.animalshaven.client.viewmodels;

import edu.alexey.animalshaven.client.helpers.AnimalsHelper;
import edu.alexey.animalshaven.domain.business.RepositoryRecord;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;
import edu.alexey.utils.StringUtils;

public class AnimalViewModel extends ViewModelBase {

	private final String strRepr;

	protected AnimalViewModel(RepositoryRecord<Animal> record) {
		var animal = record.entity();
		var animalKind = AnimalsHelper.getAnimalKind(animal);
		var animalType = AnimalsHelper.getAnimalType(animal);
		String idStr = "ID: " + StringUtils.padRight(Integer.toString(record.id()), " ", 3) + " ";
		String indent = " ".repeat(idStr.length());

		StringBuilder sb = new StringBuilder(idStr)
				.append(animal.toString())
				.append(System.lineSeparator()).append(indent)
				.append(animalKind.description())
				.append(" (").append(animalType.description()).append(")")
				.append(System.lineSeparator()).append(indent)
				.append("Умеет, если попросить:")
				.append(System.lineSeparator());

		var commands = animal.getCommands();
		if (commands.isEmpty()) {
			sb.append(indent)
					.append("\tничего");
		} else {
			commands.forEach(cmd -> {
				sb.append(indent).append("\t").append(cmd.description())
						.append(System.lineSeparator());
			});
		}
		strRepr = sb.toString();
	}

	@Override
	public String toString() {
		return strRepr;
	}
}
