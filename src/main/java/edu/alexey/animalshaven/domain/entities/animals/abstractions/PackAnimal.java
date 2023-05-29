package edu.alexey.animalshaven.domain.entities.animals.abstractions;

public abstract class PackAnimal extends Animal {
	private int liftingWeight;

	public int getLiftingWeight() {
		return liftingWeight;
	}

	public void setLiftingWeight(int liftingWeight) {
		this.liftingWeight = liftingWeight;
	}
}
