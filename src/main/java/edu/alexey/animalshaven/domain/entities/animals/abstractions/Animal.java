package edu.alexey.animalshaven.domain.entities.animals.abstractions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.alexey.animalshaven.domain.entities.commands.abstractions.Command;

public abstract class Animal {

	private LocalDate birthDate;
	private String name;
	private final List<Command> commands;

	public Animal() {
		commands = new ArrayList<>();
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
}
