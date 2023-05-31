package edu.alexey.animalshaven.domain.entities.commands.abstractions;

public interface Command {

	String description();

	void execute();
}
