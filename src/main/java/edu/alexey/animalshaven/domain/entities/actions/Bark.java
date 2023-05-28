package edu.alexey.animalshaven.domain.entities.actions;

public class Bark implements Action {

	@Override
	public String getCommand() {
		return "Гавкать";
	}

	@Override
	public void perform() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'perform'");
	}


}
