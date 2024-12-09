CREATE TABLE IF NOT EXISTS projects (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   name TEXT NOT NULL,
   description TEXT
);


CREATE TABLE IF NOT EXISTS tasks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    description TEXT NOT NULL,
    status TEXT CHECK(status IN ('pending', 'completed')) DEFAULT 'pending',
    project_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects(id)
);


INSERT INTO projects (name, description) VALUES
    ('Bygg en hemsida', 'Projekt för att skapa en responsiv hemsida'),
    ('Lär dig Java', 'Projekt för att lära dig grunderna i Java-programmering'),
    ('Organisera ett event', 'Planera och genomföra en företagsevent'),
    ('Träning', 'Skapa ett träningsprogram för att hålla dig i form');

INSERT INTO tasks (description, status, project_id) VALUES
    ('Designa startsidan', 'pending', 1),
    ('Implementera CSS-grid', 'completed', 1),
    ('Lär dig om klasser och objekt', 'pending', 2),
    ('Skapa en CLI-applikation', 'pending', 2),
    ('Hitta en lokal för eventet', 'pending', 3),
    ('Skicka inbjudningar', 'completed', 3),
    ('Planera träningsschema', 'pending', 4),
    ('Börja med löpträning', 'pending', 4);
