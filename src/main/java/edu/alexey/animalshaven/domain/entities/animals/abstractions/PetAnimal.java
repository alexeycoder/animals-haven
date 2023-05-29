package edu.alexey.animalshaven.domain.entities.animals.abstractions;

public abstract class PetAnimal extends Animal {
	private String owner;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
