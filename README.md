# War Card Game

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Requirements](#requirements)
4. [Installation](#installation)
5. [How to Run](#how-to-run)
6. [How to Play](#how-to-play)
7. [Project Structure](#project-structure)
8. [Contributing](#contributing)
9. [License](#license)

## Introduction

This is a Java implementation of the classic card game "War" with a graphical user interface. The game is designed for two players and follows standard War game rules, including handling "war" scenarios when players draw cards of equal value.

## Features

- Graphical User Interface (GUI) for easy interaction
- Two-player gameplay
- Automated card dealing and gameplay
- "War" scenario handling
- Game state saving and loading
- Sound effects for various game events
- About section with developer information

## Requirements

- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE) 8 or higher

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/WarCardGame.git
   ```
2. Navigate to the project directory:
   ```
   cd WarCardGame
   ```

## How to Run

1. Compile the Java files:
   ```
   javac -d bin src/**/*.java
   ```
2. Run the game:
   ```
   java -cp bin WarCardGame
   ```

Alternatively, if you're using an IDE like Eclipse or IntelliJ IDEA, you can import the project and run the `WarCardGame` class.

## How to Play

1. Launch the game.
2. Click the "Start Round" button to begin a new round.
3. Each player clicks their "Play Card" button to play a card.
4. The player with the higher card value wins the round and collects both cards.
5. In case of a tie, a "War" is initiated automatically.
6. The game continues until one player has all the cards.

## Project Structure

The project is organized into two main packages:

1. `model`: Contains the game logic classes
   - `Card.java`: Represents a playing card
   - `Deck.java`: Manages a collection of cards
   - `Player.java`: Represents a player in the game
   - `Game.java`: Manages the game state and logic
   - `GameSaver.java`: Handles saving and loading game progress

2. `view`: Contains GUI-related classes
   - `GameGUI.java`: Main GUI class for the game
   - `CardView.java`: Visual representation of a card
   - `PlayerView.java`: Visual representation of a player's area
   - `MenuBar.java`: Implements the game menu
   - `BackgroundPanel.java`: Handles the game background
   - `SoundPlayer.java`: Manages sound effects

## Contributing

Contributions to improve the game are welcome. Please follow these steps:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
