DROP DATABASE IF EXISTS guessTheNumber;
create database guessTheNumber;
use guessTheNumber;
-- Table structure for Game
DROP TABLE IF EXISTS game;
CREATE TABLE game (
gameId INT AUTO_INCREMENT PRIMARY KEY,
answer CHAR(4) NOT NULL,
status VARCHAR(30) NOT NULL
);
-- Table structure for round
DROP TABLE IF EXISTS round;
CREATE TABLE round (
roundId INT AUTO_INCREMENT PRIMARY KEY,
gameId INT NOT NULL ,
guess CHAR(4) NOT NULL,
resultGuess VARCHAR(7) NOT NULL,
roundTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (gameId) REFERENCES game(gameId)
);