package edu.alexey.animalshaven.domain.entities.commands.abstractions;

public abstract class CommandBase implements Command {

	private static final Runnable DUMMY_ACTION = () -> {
	};

	private final Runnable action;

	protected CommandBase(Runnable action) {
		if (action != null) {
			this.action = action;
		} else {
			this.action = DUMMY_ACTION;
		}
	}

	@Override
	public void execute() {
		this.action.run();
	}
}
