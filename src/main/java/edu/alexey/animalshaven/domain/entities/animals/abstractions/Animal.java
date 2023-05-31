package edu.alexey.animalshaven.domain.entities.animals.abstractions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.alexey.animalshaven.domain.entities.commands.Eat;
import edu.alexey.animalshaven.domain.entities.commands.Pee;
import edu.alexey.animalshaven.domain.entities.commands.Sleep;
import edu.alexey.animalshaven.domain.entities.commands.abstractions.Command;

public abstract class Animal {

	private LocalDate birthDate;
	private String name;
	protected final List<Command> commands;

	protected Animal(LocalDate birthDate, String name) {
		Objects.requireNonNull(birthDate);
		Objects.requireNonNull(name);

		this.birthDate = birthDate;
		this.name = name;
		this.commands = new ArrayList<>(
				List.of(
						new Eat(this),
						new Sleep(this),
						new Pee(this)));
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Command> getCommands() {
		// возвращаем неизменяемый список
		return commands.stream().toList();
	}

	@Override
	public String toString() {
		return String.format("Кличка: %s, Род.: %s", name, birthDate);
	}
}
