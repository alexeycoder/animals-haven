package edu.alexey.animalshaven.domain.entities.animals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.alexey.animalshaven.domain.entities.actions.Action;

public class Animal {

	private LocalDate birthDate;
	private String name;
	private final List<Action> actions;

	public Animal() {
		actions = new ArrayList<>();
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

	public List<Action> getActions() {
		// возвращаем неизменяемый список
		return actions.stream().toList();
	}

	public void learnEat() {
		
	}

}
