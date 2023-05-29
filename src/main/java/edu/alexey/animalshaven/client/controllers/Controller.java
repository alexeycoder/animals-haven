package edu.alexey.animalshaven.client.controllers;

import java.util.Map;
import java.util.Set;

import edu.alexey.animalshaven.client.uielements.Menu;
import edu.alexey.animalshaven.client.uielements.MenuItem;
import edu.alexey.animalshaven.client.view.View;
import edu.alexey.animalshaven.client.viewmodels.ViewModelBase;
import edu.alexey.animalshaven.domain.business.AnimalsManager;

public class Controller {
	// inner types
	public static record ReturnStatus(boolean exit) {
	}

	// fabric
	public static void createAndRun(AnimalsManager manager, View view) {
		var controller = new Controller(manager, view);
		controller.runLifecycle();
	}

	// const
	public static final Set<String> CMD_GO_BACK = Set.of("0");
	public static final Set<String> CMD_EXIT = Set.of("й", "q");
	public static final String MENU_MAKE_YOUR_CHOICE = "Выберите пункт меню: ";
	public static final String GOODBYE = "Вы завершили программу.\nЖдём вас снова в Пристанище для животных!\n";
	public static final String SHORT_HR = "\u2014\n";
	public static final String LOGO = "* Пристанище для животных *";

	public final Menu MAIN_MENU = new Menu(
			"Главное меню",
			Map.of(
					Set.of("1"), new MenuItem(1, "Список животных", this::showAnimals),
					Set.of("2"), new MenuItem(2, "Информация о животном", this::dummyHandler),
					Set.of("3"), new MenuItem(3, "Обучить животное", this::dummyHandler),
					Set.of(" "), new MenuItem(90, null, null),
					CMD_EXIT, new MenuItem(99, "Завершить работу", null)));

	// fields
	private final View view;
	private final AnimalsManager manager;

	// ctor
	protected Controller(AnimalsManager manager, View view) {
		this.manager = manager;
		this.view = view;
	}

	private void runLifecycle() {
		menuLifecycle(MAIN_MENU);
	}

	// base lifecycle runners

	private ReturnStatus menuLifecycle(Menu menu, boolean onetime, boolean clear) {

		final var menuViewModel = ViewModelBase.of(menu);
		final var menuMap = menu.items();

		while (true) {
			if (clear) {
				view.clear();
			}
			view.show(LOGO);
			view.show(ViewModelBase.emptySpace(2));
			view.show(menuViewModel);
			var userChoice = view.askUserChoice(MENU_MAKE_YOUR_CHOICE, menuMap.keySet());
			view.show(SHORT_HR);

			if (userChoice.equals(CMD_EXIT)) {
				view.show(GOODBYE);
				return new ReturnStatus(true);
			} else if (userChoice.equals(CMD_GO_BACK)) {
				return new ReturnStatus(false);
			}

			var menuItem = menuMap.get(userChoice);
			var handler = menuItem.handler();
			if (handler != null) {
				if (handler.apply(null) instanceof ReturnStatus rs) {
					if (rs.exit()) {
						return rs;
					}
				}
			}

			if (onetime) {
				return new ReturnStatus(false);
			}
		}
	}

	private ReturnStatus menuLifecycle(Menu menu) {
		return menuLifecycle(menu, false, true);
	}

	// handlers

	private ReturnStatus dummyHandler(Object nothing) {
		view.show("Скоро, но не сейчас...\n"
				+ "Данная функция будет доступна в следующей версии.");
		view.waitToProceed();
		return new ReturnStatus(false);
	}

	private ReturnStatus showAnimals(Object nothing) {

		var repo = manager.animalsRepository();
		var records = repo.getAll();
		view.show("Список обитателей\n");
		view.show(ViewModelBase.emptySpace(1));
		view.show(ViewModelBase.of(records));
		view.show(SHORT_HR);
		view.waitToProceed();
		return new ReturnStatus(false);
	}
}
