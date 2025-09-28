# 🟡 2D PacMan Game

A fun **2D PacMan game in Java** featuring multiple levels, lives, wrap-around movement, and high score tracking.

---

## 🎮 Features

- Classic **PacMan gameplay** with walls, food, and ghosts.
- **Multiple tile maps**: Every life can show a different layout.
- **Ghost AI**: Random movement with collision detection.
- **Lives & Score System**: 3 lives per game and high score tracking.
- **Wrap-around Movement**: PacMan and ghosts appear on the opposite side if they exit the frame.
- **Restart Functionality**: Game restarts after "Game Over" or "You Win" on pressing any key.
- **Graphics & Animations**: PacMan moves with directional animations.

---

## 🖼 Screenshots

![PacMan Start Screen](https://github.com/Samprit74/2D_PacManGame/blob/main/src/resources/Screenshot%202025-09-28%20162719.png)  
*PacMan in the starting safe position.*

![Gameplay](https://github.com/Samprit74/2D_PacManGame/blob/main/src/resources/Screenshot%202025-09-28%20162520.png)  
*Collect food, avoid ghosts, and navigate through the maze.*

![Game Over](resources/screenshots/gameover.png)  
*Game over screen when all lives are lost.*

---

## ⚙️ How to Run

1. Make sure you have **Java JDK 8+** installed.
2. Clone the repository:

```bash
git clone https://github.com/Samprit74/2D_PacManGame.git
cd 2D_PacManGame
Compile the Java file:

javac PacMan.java


Run the game:

java PacMan

🗂 Project Structure
2D_PacManGame/
│
├── resources/
│   ├── wall.png
│   ├── blueGhost.png
│   ├── orangeGhost.png
│   ├── pinkGhost.png
│   ├── redGhost.png
│   ├── pacmanUp.png
│   ├── pacmanDown.png
│   ├── pacmanLeft.png
│   └── pacmanRight.png
│
├── PacMan.java
└── README.md

💡 How to Play

Use Arrow Keys to move PacMan:

↑ : Move Up

↓ : Move Down

← : Move Left

→ : Move Right

Collect all food to win the level.

Avoid ghosts. Touching a ghost decreases a life.

The game ends when all lives are lost. Press any key to restart.

Made With

Java Swing for GUI

Java AWT for graphics & events
