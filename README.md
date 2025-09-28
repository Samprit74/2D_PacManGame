# 🟡 2D PacMan Game

A fun **2D PacMan game in Java** featuring multiple levels, lives, wrap-around movement, and high score tracking.
---
![PacMan Logo](https://tse4.mm.bing.net/th/id/OIP.aP-W9RDfjoz-Fv-wkjASTwHaEc?pid=Api&P=0&h=180)
# 💻 Tech Stack

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=plastic&logo=openjdk&logoColor=white) 
![Swing](https://img.shields.io/badge/Java_Swing-007396?style=plastic&logo=java&logoColor=white) 
![AWT](https://img.shields.io/badge/Java_AWT-FF6F00?style=plastic&logo=java&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=plastic&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=plastic&logo=github&logoColor=white)

## 🎮 Features

- Classic **PacMan gameplay** with walls, food, and ghosts.
- **Multiple tile maps**: Every life can show a different layout.
- **Random Ghost Movement**: Ghosts move randomly and bounce off walls.
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

✨ Made With

Java SE

Java Swing GUI

Java AWT for graphics/events
