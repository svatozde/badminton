INSERT INTO course VALUES (10001, 'a7b39wpa', 'Webove a podnikove aplikace');
INSERT INTO course VALUES (10002, 'x33mis', 'Manazerske informacni systemy');
INSERT INTO course VALUES (10003, 'x33izs', 'Informacni a znalostni systemy');

INSERT INTO department VALUES (10001, 'K333');
INSERT INTO department VALUES (10002, 'K336');

-- heslo je vzdy male prvni pismeno krestniho jmena (hashovane SHA-1)
INSERT INTO person(id,birthnumber,surname,firstname,username,password,groupname, dep_id) VALUES (10003, 333, 'Kremen', 'Petr', 'petr', '516b9783fca517eecbd1d064da2d165310b19759', 'teacher', 10001); -- heslo je "p" (hashovane SHA-1)
INSERT INTO person(id,birthnumber,surname,firstname,username,password,groupname, dep_id) VALUES (10004, 444, 'Blasko', 'Miroslav', 'miroslav', '6b0d31c0d563223024da45691584643ac78c96e8', 'teacher', 10001); -- heslo je "m" (hashovane SHA-1)
INSERT INTO person(id,birthnumber,surname,firstname,username,password,groupname, dep_id) VALUES (10005, 555, 'Kouba', 'Zdenek', 'zdenek', '395df8f7c51f007019cb30201c49e884b46b92fa', 'teacher', 10001); -- heslo je "z" (hashovane SHA-1)
INSERT INTO person(id,birthnumber,surname,firstname,username,password,groupname, dep_id) VALUES (10006, 666, 'Smid', 'Marek', 'marek', '6b0d31c0d563223024da45691584643ac78c96e8', 'teacher', 10001); -- heslo je "m" (hashovane SHA-1)

INSERT INTO person(id,birthnumber,surname,firstname,username,password,groupname, supervisor_id) VALUES (10001, 111, 'Novak', 'Frantisek','frantisek', '4a0a19218e082a343a1b17e5333409af9d98f0f5', 'student', 10004); -- heslo je "f" (hashovane SHA-1)
INSERT INTO person(id,birthnumber,surname,firstname,username,password,groupname, supervisor_id) VALUES (10002, 222, 'Kozina', 'Jan', 'jan', '5c2dd944dde9e08881bef0894fe7b22a5c9c4b06', 'student', 10003); -- heslo je "j" (hashovane SHA-1)
