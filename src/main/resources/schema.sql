CREATE TABLE IF NOT EXISTS WEATHER(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    `date` DATE NOT NULL,
    lat FLOAT NOT NULL,
    lon FLOAT NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    temperatures JSON

);


