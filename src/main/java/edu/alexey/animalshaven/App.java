package edu.alexey.animalshaven;

import java.util.Locale;

import edu.alexey.animalshaven.client.controllers.Controller;
import edu.alexey.animalshaven.client.view.ConsoleView;
import edu.alexey.animalshaven.client.view.View;
import edu.alexey.animalshaven.domain.business.AnimalsManager;

public class App {
	public static void main(String[] args) {
		Locale.setDefault(AppSettings.LOCALE);

		// interfaces: DbContext -> DataManager, View
		View view = new ConsoleView();
		AnimalsManager manager;

		try {
			manager = new AnimalsManager();

		} catch (Exception e) {
			view.show(
					"Произошла непредвиденная ошибка во время инициализации базы данных. Приложение будет закрыто.\n");
			view.show("Подробности об ошибке:\n");
			view.show(e.getLocalizedMessage());
			view.show(System.lineSeparator());
			return;
		}

		try {
			Controller.createAndRun(manager, view);

		} catch (Exception e) {
			view.show("Произошла непредвиденная ошибка во время работы приложения. Приложение будет закрыто.\n");
			view.show("Подробности об ошибке:\n");
			view.show(e.getLocalizedMessage());
			view.show(e.getMessage());
			e.printStackTrace();
			view.show(System.lineSeparator());
		}
	}
}
