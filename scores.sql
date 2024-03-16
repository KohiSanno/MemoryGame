USE scoresDatabase;
CREATE TABLE highscores (
    id INT AUTO_INCREMENT,
    name VARCHAR(255),
    score INT,
    PRIMARY KEY (id)
);