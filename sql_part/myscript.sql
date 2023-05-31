DROP DATABASE friends_of_man;
CREATE DATABASE friends_of_man;

USE friends_of_man;

/*
 * Создаём таблицы.
 * 
 * Поскольку реляционная модель БД не подрузумевает непосредственного
 * наследования, аналогичного объектной модели в ООП, и расширения MySQL
 * также не умеют наследование таблиц, то для имитации объектной модели
 * будем использовать псевдо-наследование с помощью FOREIGN KEY.
 */

-- Commands -- справочник команд

DROP TABLE IF EXISTS commands;
CREATE TABLE commands (
	command_id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
	description VARCHAR(50),
	PRIMARY KEY (command_id)
);

-- Animals

DROP TABLE IF EXISTS animals;
CREATE TABLE animals (
	animal_id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
	birth_date DATE,
	name VARCHAR(50),
	PRIMARY KEY (animal_id)
);

-- AnimalCommand -- связующая таблица многие-ко-многим (животное<->команда)

DROP TABLE IF EXISTS animal_command;
CREATE TABLE animal_command (
	animal_id INT UNSIGNED NOT NULL,
	command_id INT UNSIGNED NOT NULL,
	PRIMARY KEY (animal_id, command_id),
	FOREIGN KEY (animal_id) REFERENCES animals(animal_id),
	FOREIGN KEY (command_id) REFERENCES commands(command_id)
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

-- Справочник команд:

INSERT INTO commands (command_id, description)
VALUES
	(1, 'Есть'),
	(2, 'Спать'),
	(3, 'Голос'),
	(4, 'Гулять'),
	(5, 'Скакать'),
	(6, 'Нести');

-- Домашние животные:

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (1, '2020-09-02', 'Анфиса');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (1, 'Виолетта Михайловна');

INSERT INTO cats (cat_id)
	VALUES (1);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (1, 1), (1, 2), (1, 4);
	
--

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (2, '2022-01-16', 'Мурка');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (2, 'Юрий Дмитриевич');

INSERT INTO cats (cat_id)
	VALUES (2);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (2, 1), (2, 2), (2, 4);

--

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (3, '2019-10-15', 'Барбос');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (3, 'нет');

INSERT INTO dogs (dog_id)
	VALUES (3);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (3, 1), (3, 2), (3, 3), (3, 4);

--

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (4, '2015-08-15', 'Рекс');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (4, 'Рихард Мозер');

INSERT INTO dogs (dog_id)
	VALUES (4);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (4, 1), (4, 2), (4, 3), (4, 4);

--

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (5, '2019-08-20', 'Жужжа');

INSERT INTO pet_animals (pet_animal_id, owner)
	VALUES (5, 'Василий Хомяков');

INSERT INTO hamsters (hamster_id)
	VALUES (5);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (5, 1), (5, 2);

-- Вьючные животные:

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (6, '2022-02-11', 'Чайка');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (6, 150);

INSERT INTO horses (horse_id)
	VALUES (6);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (6, 1), (6, 2), (6, 5), (6, 6);

--

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (7, '2021-04-27', 'Заря');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (7, 120);

INSERT INTO horses (horse_id)
	VALUES (7);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (7, 1), (7, 2), (7, 5), (7, 6);

--

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (8, '2016-11-04', 'Валет');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (8, 130);

INSERT INTO camels (camel_id)
	VALUES (8);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (8, 1), (8, 2), (8, 6);

--

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (9, '2018-10-19', 'Генри');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (9, 110);

INSERT INTO camels (camel_id)
	VALUES (9);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (9, 1), (9, 2), (9, 6);

--

INSERT INTO animals (animal_id, birth_date, name)
	VALUES (10, '2023-05-20', 'Отто');

INSERT INTO pack_animals (pack_animal_id, load_capacity)
	VALUES (10, 80);

INSERT INTO mules (mule_id)
	VALUES (10);

INSERT INTO animal_command (animal_id, command_id)
	VALUES (10, 1), (10, 2), (10, 4), (10, 6);

/*
 * Удаление всех записей о верблюдах
 */

-- Сперва выведем список верблюдов:

SELECT
	animal_id,
	birth_date,
	name,
	load_capacity,
	GROUP_CONCAT(description SEPARATOR ', ') AS commands
FROM camels
LEFT JOIN pack_animals ON camel_id = pack_animal_id
LEFT JOIN animals ON camel_id = animal_id
LEFT JOIN animal_command USING (animal_id)
LEFT JOIN commands USING (command_id)
GROUP BY animal_id, load_capacity;

/*
animal_id|birth_date|name |load_capacity|commands          |
---------+----------+-----+-------------+------------------+
        8|2016-11-04|Валет|          130|Есть, Спать, Нести|
        9|2018-10-19|Генри|          110|Есть, Спать, Нести|

2 row(s) fetched.
*/

-- Запоминаем ключи верблюдов во временной таблице:

DROP TEMPORARY TABLE IF EXISTS camels_ids;
CREATE TEMPORARY TABLE camels_ids(camel_id INT UNSIGNED);
INSERT INTO camels_ids(camel_id)
		SELECT camel_id FROM camels;

-- Удалим связи верблюдов с командами:
	
DELETE FROM animal_command 
WHERE animal_id IN
	(SELECT camel_id AS animal_id FROM camels_ids);

-- Удалим записи о верблюдах из связанных псевдо-наследованием таблиц
-- последовательно из camels, pack_animals и animals:

DELETE FROM camels;
DELETE FROM pack_animals
WHERE pack_animal_id IN
	(SELECT camel_id AS pack_animal_id FROM camels_ids);
DELETE FROM animals
WHERE animal_id IN
	(SELECT camel_id AS animal_id FROM camels_ids);

-- Не забываем избавиться от временной таблицы:

DROP TEMPORARY TABLE IF EXISTS camels_ids;

-- Проверяем:

SELECT
	animal_id,
	birth_date,
	name,
	load_capacity,
	GROUP_CONCAT(description SEPARATOR ', ') AS commands
FROM camels
LEFT JOIN pack_animals ON camel_id = pack_animal_id
LEFT JOIN animals ON camel_id = animal_id
LEFT JOIN animal_command USING (animal_id)
LEFT JOIN commands USING (command_id)
GROUP BY animal_id, load_capacity;

/*
animal_id|birth_date|name|load_capacity|commands|
---------+----------+----+-------------+--------+

0 row(s) fetched.
*/

/*
 * Объединяем таблицы лошадей и ослов в одну
 */

CREATE TABLE horses_and_mules_info AS
	SELECT
		animal_id,
		'Вьючное животное' AS animal_type,
		'Лошадь' AS animal_kind,
		birth_date,
		name,
		load_capacity,
		GROUP_CONCAT(description SEPARATOR ', ') AS commands
	FROM horses
	LEFT JOIN pack_animals ON horse_id = pack_animal_id
	LEFT JOIN animals ON horse_id = animal_id
	LEFT JOIN animal_command USING (animal_id)
	LEFT JOIN commands USING (command_id)
	GROUP BY animal_id, load_capacity
UNION ALL
	SELECT
		animal_id,
		'Вьючное животное' AS animal_type,
		'Осёл' AS animal_kind,
		birth_date,
		name,
		load_capacity,
		GROUP_CONCAT(description SEPARATOR ', ') AS commands
	FROM mules
	LEFT JOIN pack_animals ON mule_id = pack_animal_id
	LEFT JOIN animals ON mule_id = animal_id
	LEFT JOIN animal_command USING (animal_id)
	LEFT JOIN commands USING (command_id)
	GROUP BY animal_id, load_capacity;

-- Выборка из новой объединённой таблицы:

SELECT * FROM horses_and_mules_info;

/*
animal_id|animal_type     |animal_kind|birth_date|name |load_capacity|commands                   |
---------+----------------+-----------+----------+-----+-------------+---------------------------+
        6|Вьючное животное|Лошадь     |2022-02-11|Чайка|          150|Есть, Спать, Скакать, Нести|
        7|Вьючное животное|Лошадь     |2021-04-27|Заря |          120|Есть, Спать, Скакать, Нести|
       10|Вьючное животное|Осёл       |2023-05-20|Отто |           80|Есть, Спать, Гулять, Нести |

3 row(s) fetched.
*/

/*
 * Создать новую таблицу "молодые животные" в которую попадут все
 * животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью
 * до месяца подсчитать возраст животных в новой таблице.
 */

DROP TABLE IF EXISTS young_animals;

CREATE TABLE young_animals AS
	SELECT
		animal_id,
		name,
		birth_date,
		CONCAT(
			TIMESTAMPDIFF(YEAR, birth_date, NOW()),' лет и ',
			TIMESTAMPDIFF(MONTH, birth_date, NOW()) % 12, ' месяцев'
		) AS age
	FROM animals
	WHERE
		birth_date + INTERVAL 1 YEAR <= NOW()
	AND
		birth_date + INTERVAL 3 YEAR >= NOW();

-- Выборка результирующей таблицы:

SELECT * FROM young_animals;

/*
animal_id|name  |birth_date|age              |
---------+------+----------+-----------------+
        1|Анфиса|2020-09-02|2 лет и 8 месяцев|
        2|Мурка |2022-01-16|1 лет и 4 месяцев|
        6|Чайка |2022-02-11|1 лет и 3 месяцев|
        7|Заря  |2021-04-27|2 лет и 1 месяцев|

4 row(s) fetched.
*/

/*
 * Объединение всех таблиц в одну
 */

DROP TABLE IF EXISTS all_animals_data;

CREATE TABLE all_animals_data AS
	SELECT
		animal_id,
		'Домашнее животное' AS animal_type,
		'Кошка' AS animal_kind,
		birth_date,
		name,
		owner,
		load_capacity,
		GROUP_CONCAT(description SEPARATOR ', ') AS commands
	FROM cats
	LEFT JOIN pet_animals ON cat_id = pet_animal_id
	LEFT JOIN animals ON cat_id = animal_id
	LEFT JOIN pack_animals ON animal_id = pack_animal_id
	LEFT JOIN animal_command USING (animal_id)
	LEFT JOIN commands USING (command_id)
	GROUP BY animal_id, owner, load_capacity
UNION ALL
	SELECT
		animal_id,
		'Домашнее животное' AS animal_type,
		'Собака' AS animal_kind,
		birth_date,
		name,
		owner,
		load_capacity,
		GROUP_CONCAT(description SEPARATOR ', ') AS commands
	FROM dogs
	LEFT JOIN pet_animals ON dog_id = pet_animal_id
	LEFT JOIN animals ON dog_id = animal_id
	LEFT JOIN pack_animals ON animal_id = pack_animal_id
	LEFT JOIN animal_command USING (animal_id)
	LEFT JOIN commands USING (command_id)
	GROUP BY animal_id, owner, load_capacity
UNION ALL
	SELECT
		animal_id,
		'Домашнее животное' AS animal_type,
		'Хомяк' AS animal_kind,
		birth_date,
		name,
		owner,
		load_capacity,
		GROUP_CONCAT(description SEPARATOR ', ') AS commands
	FROM hamsters
	LEFT JOIN pet_animals ON hamster_id = pet_animal_id
	LEFT JOIN animals ON hamster_id = animal_id
	LEFT JOIN pack_animals ON animal_id = pack_animal_id
	LEFT JOIN animal_command USING (animal_id)
	LEFT JOIN commands USING (command_id)
	GROUP BY animal_id, owner, load_capacity
UNION ALL
	SELECT
		animal_id,
		'Вьючное животное' AS animal_type,
		'Лошадь' AS animal_kind,
		birth_date,
		name,
		owner,
		load_capacity,
		GROUP_CONCAT(description SEPARATOR ', ') AS commands
	FROM horses
	LEFT JOIN pack_animals ON horse_id = pack_animal_id
	LEFT JOIN animals ON horse_id = animal_id
	LEFT JOIN pet_animals ON animal_id = pet_animal_id
	LEFT JOIN animal_command USING (animal_id)
	LEFT JOIN commands USING (command_id)
	GROUP BY animal_id, load_capacity, owner
UNION ALL
	SELECT
		animal_id,
		'Вьючное животное' AS animal_type,
		'Осёл' AS animal_kind,
		birth_date,
		name,
		owner,
		load_capacity,
		GROUP_CONCAT(description SEPARATOR ', ') AS commands
	FROM mules
	LEFT JOIN pack_animals ON mule_id = pack_animal_id
	LEFT JOIN animals ON mule_id = animal_id
	LEFT JOIN pet_animals ON animal_id = pet_animal_id
	LEFT JOIN animal_command USING (animal_id)
	LEFT JOIN commands USING (command_id)
	GROUP BY animal_id, load_capacity, owner;

SELECT * FROM all_animals_data;

/*
animal_id|animal_type      |animal_kind|birth_date|name  |owner              |load_capacity|commands                   |
---------+-----------------+-----------+----------+------+-------------------+-------------+---------------------------+
        1|Домашнее животное|Кошка      |2020-09-02|Анфиса|Виолетта Михайловна|             |Есть, Спать, Гулять        |
        2|Домашнее животное|Кошка      |2022-01-16|Мурка |Юрий Дмитриевич    |             |Есть, Спать, Гулять        |
        3|Домашнее животное|Собака     |2019-10-15|Барбос|нет                |             |Есть, Спать, Голос, Гулять |
        4|Домашнее животное|Собака     |2015-08-15|Рекс  |Рихард Мозер       |             |Есть, Спать, Голос, Гулять |
        5|Домашнее животное|Хомяк      |2019-08-20|Жужжа |Василий Хомяков    |             |Есть, Спать                |
        6|Вьючное животное |Лошадь     |2022-02-11|Чайка |                   |          150|Есть, Спать, Скакать, Нести|
        7|Вьючное животное |Лошадь     |2021-04-27|Заря  |                   |          120|Есть, Спать, Скакать, Нести|
       10|Вьючное животное |Осёл       |2023-05-20|Отто  |                   |           80|Есть, Спать, Гулять, Нести |

8 row(s) fetched.
*/
