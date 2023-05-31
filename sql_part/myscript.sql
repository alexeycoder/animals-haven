DROP DATABASE friends_of_man;
CREATE DATABASE friends_of_man;

USE friends_of_man;

/*
 * Создаём таблицы.
 */

-- Commands (справочник команд)

DROP TABLE IF EXISTS commands;
CREATE TABLE commands (
	command_id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
	description VARCHAR(50),
	PRIMARY KEY(command_id)
);

-- Animals

DROP TABLE IF EXISTS animals;
CREATE TABLE animals (
	animal_id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
	birth_date DATE,
	name VARCHAR(50),
	PRIMARY KEY(animal_id)
);

-- PetAnimals

DROP TABLE IF EXISTS pet_animals;
CREATE TABLE pet_animals (
	pet_animal_id INT UNSIGNED NOT NULL,
	owner VARCHAR(50),
	PRIMARY KEY (pet_animal_id),
	FOREIGN KEY (pet_animal_id) REFERENCES animals(animal_id) 
);

-- PackAnimals

DROP TABLE IF EXISTS pack_animals;
CREATE TABLE pack_animals (
	pack_animal_id INT UNSIGNED NOT NULL,
	load_capacity INT UNSIGNED,
	PRIMARY KEY (pack_animal_id),
	FOREIGN KEY (pack_animal_id) REFERENCES animals(animal_id) 
);

-- Concrete pet animals

DROP TABLE IF EXISTS cats;
CREATE TABLE cats (
	cat_id INT UNSIGNED NOT NULL,
	PRIMARY KEY (cat_id),
	FOREIGN KEY (cat_id) REFERENCES pet_animals(pet_animal_id) 
);

DROP TABLE IF EXISTS dogs;
CREATE TABLE dogs (
	dog_id INT UNSIGNED NOT NULL,
	PRIMARY KEY (dog_id),
	FOREIGN KEY (dog_id) REFERENCES pet_animals(pet_animal_id) 
);

DROP TABLE IF EXISTS hamsters;
CREATE TABLE hamsters (
	hamster_id INT UNSIGNED NOT NULL,
	PRIMARY KEY (hamster_id),
	FOREIGN KEY (hamster_id) REFERENCES pet_animals(pet_animal_id) 
);

-- Concrete pack animals

DROP TABLE IF EXISTS horses;
CREATE TABLE horses (
	horse_id INT UNSIGNED NOT NULL,
	PRIMARY KEY (horse_id),
	FOREIGN KEY (horse_id) REFERENCES pack_animals(pack_animal_id) 
);

DROP TABLE IF EXISTS camels;
CREATE TABLE camels (
	camel_id INT UNSIGNED NOT NULL,
	PRIMARY KEY (camel_id),
	FOREIGN KEY (camel_id) REFERENCES pack_animals(pack_animal_id) 
);

DROP TABLE IF EXISTS mules;
CREATE TABLE mules (
	mule_id INT UNSIGNED NOT NULL,
	PRIMARY KEY (mule_id),
	FOREIGN KEY (mule_id) REFERENCES pack_animals(pack_animal_id) 
);

/*
 * Наполняем таблицы по одной животине с учётом наследования:
 */

-- Домашние животные:

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (1, '2020-09-02', 'Анфиса');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (1, 'Виолетта Михайловна');

INSERT INTO cats (cat_id)
	VALUES (1);


INSERT INTO animals (animal_id, birth_date, name)
	VALUES (2, '2022-01-16', 'Мурка');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (2, 'Юрий Дмитриевич');

INSERT INTO cats (cat_id)
	VALUES (2);


INSERT INTO animals (animal_id, birth_date, name)
	VALUES (3, '2019-10-15', 'Барбос');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (3, 'нет');

INSERT INTO dogs (dog_id)
	VALUES (3);


INSERT INTO animals (animal_id, birth_date, name)
	VALUES (4, '2015-08-15', 'Рекс');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (4, 'Рихард Мозер');

INSERT INTO dogs (dog_id)
	VALUES (4);


INSERT INTO animals (animal_id, birth_date, name)
	VALUES (5, '2019-08-20', 'Жужжа');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (5, 'Василий Хомяков');

INSERT INTO hamsters (hamster_id)
	VALUES (5);

-- Вьючные животные:

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (6, '2022-02-11', 'Чайка');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (6, 150);

INSERT INTO horses (horse_id)
	VALUES (6);


INSERT INTO animals (animal_id, birth_date, name)
	VALUES (7, '2021-04-27', 'Заря');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (7, 120);

INSERT INTO horses (horse_id)
	VALUES (7);


INSERT INTO animals (animal_id, birth_date, name)
	VALUES (8, '2016-11-04', 'Валет');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (8, 130);

INSERT INTO camels (camel_id)
	VALUES (8);


INSERT INTO animals (animal_id, birth_date, name)
	VALUES (9, '2018-10-19', 'Генри');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (9, 110);

INSERT INTO camels (camel_id)
	VALUES (9);


INSERT INTO animals (animal_id, birth_date, name)
	VALUES (10, '2023-05-20', 'Отто');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (10, 80);

INSERT INTO mules (mule_id)
	VALUES (10);


/*
 * Тестовая выборка с учётом псевдо-наследования
 */

SELECT
	animal_id,
	'домашнее животное' AS animal_type,
	'кошка' AS animal_kind,
	birth_date,
	name,
	owner
FROM cats
LEFT JOIN pet_animals ON cat_id = pet_animal_id
LEFT JOIN animals ON cat_id = animal_id;

/*
animal_id|animal_type      |animal_kind|birth_date|name  |owner              |
---------+-----------------+-----------+----------+------+-------------------+
        1|домашнее животное|кошка      |2020-09-02|Анфиса|Виолетта Михайловна|
        2|домашнее животное|кошка      |2022-01-16|Мурка |Юрий Дмитриевич    |

2 row(s) fetched.
*/



