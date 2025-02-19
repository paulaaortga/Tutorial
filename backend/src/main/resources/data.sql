INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES ('Ernesto Lopez');
INSERT INTO client(name) VALUES ('Juan Sanchez');
INSERT INTO client(name) VALUES ('Ana Martinez');

INSERT INTO prestamo(game_id, client_id, fechaInicio, fechaFin) VALUES (1, 2, '2025-10-03', '2025-10-10');
INSERT INTO prestamo(game_id, client_id, fechaInicio, fechaFin) VALUES ( 2, 1, '2025-09-12', '2025-09-15' );
INSERT INTO prestamo(game_id, client_id, fechaInicio, fechaFin) VALUES ( 3, 3, '2025-12-25', '2025-12-30');
INSERT INTO prestamo(game_id, client_id, fechaInicio, fechaFin) VALUES (4, 1, '2025-10-03', '2025-10-10');
INSERT INTO prestamo(game_id, client_id, fechaInicio, fechaFin) VALUES ( 1, 1, '2025-09-12', '2025-09-15' );
INSERT INTO prestamo(game_id, client_id, fechaInicio, fechaFin) VALUES ( 6, 3, '2025-12-25', '2025-12-30');