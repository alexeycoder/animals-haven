package edu.alexey.animalshaven;

import java.util.Locale;

import edu.alexey.animalshaven.client.controllers.Controller;
import edu.alexey.animalshaven.client.view.ConsoleView;
import edu.alexey.animalshaven.client.view.View;
import edu.alexey.animalshaven.domain.business.AnimalsManager;

public class App {
	public static void main(String[] args) {
		Locale.setDefault(AppSettings.LOCALE);

		View view;
		AnimalsManager manager;

		try {
			view = new ConsoleView();
			manager = new AnimalsManager();
			manager.FillWithData();

		} catch (Exception e) {
			System.err.println(
					"Произошла непредвиденная ошибка во время инициализации приложения. Приложение будет закрыто.");
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace(System.err);
			return;
		}

		try {
			Controller.createAndRun(manager, view);

		} catch (Exception e) {
			System.err
					.println("Произошла непредвиденная ошибка во время работы приложения. Приложение будет закрыто.\n");
			System.err.println(e.getLocalizedMessage());
			e.printStackTrace(System.err);
		}
	}
}
