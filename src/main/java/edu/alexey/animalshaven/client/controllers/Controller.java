package edu.alexey.animalshaven.client.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import edu.alexey.animalshaven.client.helpers.AnimalsHelper;
import edu.alexey.animalshaven.client.uielements.Menu;
import edu.alexey.animalshaven.client.uielements.MenuItem;
import edu.alexey.animalshaven.client.view.View;
import edu.alexey.animalshaven.client.viewmodels.ViewModelBase;
import edu.alexey.animalshaven.domain.business.AnimalsManager;
import edu.alexey.animalshaven.domain.business.RepositoryRecord;
import edu.alexey.animalshaven.domain.entities.animals.Camel;
import edu.alexey.animalshaven.domain.entities.animals.Cat;
import edu.alexey.animalshaven.domain.entities.animals.Dog;
import edu.alexey.animalshaven.domain.entities.animals.Hamster;
import edu.alexey.animalshaven.domain.entities.animals.Horse;
import edu.alexey.animalshaven.domain.entities.animals.Mule;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.Animal;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.PackAnimal;
import edu.alexey.animalshaven.domain.entities.animals.abstractions.PetAnimal;

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
					Set.of("1"), new MenuItem(1, "Список всех обитателей", this::showAnimals),
					Set.of("2"), new MenuItem(2, "Информация о животном - поиск по ID", this::findAnimalById),
					Set.of("3"), new MenuItem(3, "Информация о животном - поиск по кличке", this::findAnimalsByName),
					Set.of("4"), new MenuItem(4, "Завести новое животное", this::addAnimalLifecycle),
					Set.of("5"), new MenuItem(5, "Обучить животное", this::dummyHandler),
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

	// straight handlers & particular life-cycles

	private ReturnStatus dummyHandler(Object nothing) {
		view.show("Скоро, но не сейчас...\n"
				+ "Данная функция будет доступна в следующей версии.");
		view.waitToProceed();
		return new ReturnStatus(false);
	}

	private ReturnStatus showAnimals(Object nothing) {

		var repo = manager.animalsRepository();
		var records = repo.getAll();
		view.show("Обитатели");
		view.show(ViewModelBase.emptySpace(2));
		view.show(ViewModelBase.of(records, true));
		view.show(SHORT_HR);
		view.waitToProceed();
		return new ReturnStatus(false);
	}

	private RepositoryRecord<Animal> askAnimalById(String prompt) {
		RepositoryRecord<Animal> animalRecord = null;
		int id;
		do {
			OptionalInt answer = view.askInteger(prompt, 1, null);
			if (answer.isEmpty()) {
				return null;
			}
			id = answer.getAsInt();
			var repo = manager.animalsRepository();
			animalRecord = repo.getById(id);
		} while (animalRecord == null
				&& view.askYesNo(String.format("Записи с ID %d не найдено.\nПовторить поиск (Д/н) (Y/n)? ", id), true));

		return animalRecord;
	}

	private ReturnStatus findAnimalById(Object nothing) {
		var rs = new ReturnStatus(false);

		view.clear();
		view.show("Обитатели \u2014 Поиск по ID\n");

		do {
			view.show(ViewModelBase.emptySpace(1));
			var animalRecord = askAnimalById("Введите ID (или пустой Ввод чтобы отменить): ");
			if (animalRecord == null) {
				break;
			}

			view.show("Найдена запись:\n");
			view.show(ViewModelBase.of(animalRecord, false));
			view.show(SHORT_HR);

		} while (view.askYesNo("Повторить поиск (Д/н) (Y/n)? ", true));

		view.waitToProceed();
		return rs;
	}

	private ReturnStatus findAnimalsByName(Object nothing) {
		var rs = new ReturnStatus(false);

		view.clear();
		view.show("Обитатели \u2014 Поиск по кличке\n");
		do {
			view.show(ViewModelBase.emptySpace(1));
			var answer = view.askString(
					"Введите кличку, частично или полностью"
							+ " (пустой Ввод для отмены):\n",
					null, null);
			if (answer.isEmpty()) {
				break;
			}

			String nameSample = answer.get();
			var repo = manager.animalsRepository();
			var animalRecords = repo.getByName(nameSample);

			view.show(ViewModelBase.emptySpace(1));

			if (animalRecords.isEmpty()) {
				view.show(String.format("Записей по образцу '%s' не найдено.\n", nameSample));
			} else {
				view.show(String.format("Найдено записей: %d\n", animalRecords.size()));
				view.show(ViewModelBase.emptySpace(1));
				view.show(ViewModelBase.of(animalRecords, false));
			}

			view.show(SHORT_HR);

		} while (view.askYesNo("Повторить поиск (Д/н) (Y/n)? ", true));

		view.waitToProceed();
		return rs;
	}

	private ReturnStatus addAnimalLifecycle(Object arg) {

		Map<Set<String>, MenuItem> menuItems = new HashMap<>(Map.of(
				Set.of(" "), new MenuItem(90, null, null),
				CMD_GO_BACK, new MenuItem(91, "Вернуться в предыдущее меню", null),
				CMD_EXIT, new MenuItem(99, "Завершить работу", null)));

		// 2nd LEVEL: select animal kind
		if (arg instanceof AnimalsHelper.TypeId animalTypeId) {
			AtomicInteger menuKey = new AtomicInteger(1);
			AnimalsHelper.animalKinds.stream()
					.filter(k -> animalTypeId.cls().isAssignableFrom(k.cls()))
					.forEach(animalKindId -> {
						menuItems.put(
								Set.of(menuKey.toString()),
								new MenuItem(menuKey.get(), animalKindId.description(),
										t -> addAnimalLifecycle(animalKindId)));
						menuKey.incrementAndGet();
					});
			Menu addAnimalSelectKindMenu = new Menu("Завести новое " + animalTypeId.description(), menuItems);
			return menuLifecycle(addAnimalSelectKindMenu);
		}

		// 3rd LEVEL - create selected animal
		if (arg instanceof AnimalsHelper.KindId animalKindId) {

			view.clear();
			view.show("Завести новое животное: " + animalKindId.description());
			view.show(ViewModelBase.emptySpace(1));

			Optional<Animal> animalOpt = createAnimal(animalKindId);

			final String somethingWrong = "Что-то пошло не так. Не удалось добавить животное.";
			if (animalOpt == null) {
				view.show(somethingWrong);
			} else if (animalOpt.isEmpty()) {
				view.show("Добавление отменено.");
			} else {
				var repo = manager.animalsRepository();
				var repoRecord = repo.add(animalOpt.get());
				if (repoRecord == null) {
					view.show(somethingWrong);
				} else {
					view.show("Успешно добавлено новое животное:");
					view.show(ViewModelBase.emptySpace(2));
					view.show(ViewModelBase.of(repoRecord, false));
				}
			}
			view.show(ViewModelBase.emptySpace(1));
			view.show(SHORT_HR);
			view.waitToProceed();
			return new ReturnStatus(false);
		}

		// 1st - TOP LEVEL: select animal type
		AtomicInteger menuKey = new AtomicInteger(1);
		AnimalsHelper.animalTypes.stream().forEach(animalTypeId -> {
			menuItems.put(
					Set.of(menuKey.toString()),
					new MenuItem(menuKey.get(), animalTypeId.description(), t -> addAnimalLifecycle(animalTypeId)));
			menuKey.incrementAndGet();
		});
		Menu addAnimalSelectTypeMenu = new Menu("Завести новое животное", menuItems);
		return menuLifecycle(addAnimalSelectTypeMenu);
	}

	private Optional<Animal> createAnimal(AnimalsHelper.KindId animalKindId) {
		assert animalKindId != null : "animalKindId is null";

		var nameOpt = view.askString(
				"Введите кличку (пустой Ввод для отмены):\n",
				null, null);
		if (nameOpt.isEmpty()) {
			return Optional.empty();
		}

		var birthDateOpt = view.askDate("Введите дату рождения в формате YYYY-MM-DD (пустой Ввод чтобы отменить): ");
		if (birthDateOpt.isEmpty()) {
			return Optional.empty();
		}

		var kindCls = animalKindId.cls();

		if (PetAnimal.class.isAssignableFrom(kindCls)) {

			Optional<String> ownerOpt;
			if (view.askYesNo("У питомца есть владелец (Д/н) (Y/n)? ", true)) {
				ownerOpt = view.askString("Введите имя владельца (пустой Ввод для отмены):\n",
						null, null);
				if (nameOpt.isEmpty()) {
					return Optional.empty();
				}
			} else {
				ownerOpt = Optional.empty();
			}

			if (kindCls.equals(Cat.class)) {
				return Optional.of(new Cat(birthDateOpt.get(), nameOpt.get(), ownerOpt.orElse(null)));
			}
			if (kindCls.equals(Dog.class)) {
				return Optional.of(new Dog(birthDateOpt.get(), nameOpt.get(), ownerOpt.orElse(null)));
			}
			if (kindCls.equals(Hamster.class)) {
				return Optional.of(new Hamster(birthDateOpt.get(), nameOpt.get(), ownerOpt.orElse(null)));
			}

		} else if (PackAnimal.class.isAssignableFrom(kindCls)) {

			var loadCapOpt = view.askInteger("Введите грузоподъёмность, кг. (пустой Ввод для отмены):\n",
					0, null);
			if (loadCapOpt.isEmpty()) {
				return Optional.empty();
			}

			if (kindCls.equals(Camel.class)) {
				return Optional.of(new Camel(birthDateOpt.get(), nameOpt.get(), loadCapOpt.getAsInt()));
			}
			if (kindCls.equals(Horse.class)) {
				return Optional.of(new Horse(birthDateOpt.get(), nameOpt.get(), loadCapOpt.getAsInt()));
			}
			if (kindCls.equals(Mule.class)) {
				return Optional.of(new Mule(birthDateOpt.get(), nameOpt.get(), loadCapOpt.getAsInt()));
			}
		}

		return null;
	}
}
