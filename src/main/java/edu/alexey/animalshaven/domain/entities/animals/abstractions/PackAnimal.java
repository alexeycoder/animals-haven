package edu.alexey.animalshaven.domain.entities.animals.abstractions;

import java.time.LocalDate;
import java.util.List;

import edu.alexey.animalshaven.domain.entities.commands.CarryLoad;
import edu.alexey.animalshaven.domain.entities.commands.Graze;

public abstract class PackAnimal extends Animal {

	private int loadCapacity;

	protected PackAnimal(LocalDate birthDate, String name, int loadCapacity) {
		super(birthDate, name);
		this.loadCapacity = loadCapacity;

		this.commands.addAll(List.of(
				new Graze(this),
				new CarryLoad(this, loadCapacity)));
	}

	public int getLoadCapacity() {
		return loadCapacity;
	}

	public void setLoadCapacity(int loadCapacity) {
		this.loadCapacity = loadCapacity;
	}

	@Override
	public String toString() {
		return String.format("%s, Грузоподъёмность: %d кг.", super.toString(), loadCapacity);
	}
}
