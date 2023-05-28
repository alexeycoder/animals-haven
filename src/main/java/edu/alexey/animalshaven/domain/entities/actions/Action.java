package edu.alexey.animalshaven.domain.entities.actions;

public interface Action {

	String getCommand();

	void perform();
}
