package edu.alexey.animalshaven.domain.entities.actions;

public class Eat implements Action {

	public Eat() {
		
	}

	@Override
	public String getCommand() {
		return "Есть";
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'perform'");
	}

	
}
