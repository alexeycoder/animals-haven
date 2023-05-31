package edu.alexey.animalshaven.domain.entities.animals.abstractions;

import java.time.LocalDate;
import edu.alexey.animalshaven.domain.entities.commands.Walk;

public abstract class PetAnimal extends Animal {

	private String owner;

	protected PetAnimal(LocalDate birthDate, String name, String owner) {
		super(birthDate, name);
		this.owner = owner;

		this.commands.add(new Walk(this));
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean hasOwner() {
		return owner != null && !owner.isBlank();
	}

	@Override
	public String toString() {
		return String.format("%s, Владелец: %s", super.toString(), hasOwner() ? owner : "нет");
	}
}
