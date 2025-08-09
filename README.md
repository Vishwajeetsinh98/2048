# 2048 Game (Java Console Version)

A fully functional **2048** game implemented in **Java** for the terminal, with colored tiles and smooth board updates.

## 🎯 Features
- Classic 2048 gameplay
- Smooth in-place console board updates (no cluttered output)
- Colored tiles for better readability
- Random tile generation (90% chance of `2`, 10% chance of `4`)
- Win detection (2048 tile) and game over detection
- Simple terminal-based interface

---

## 📂 Project Structure
```
src/
 ├── controller/
 │    └── GameController.java    # Core game logic and state handling
 ├── model/
 │    ├── MoveDirection.java     # Enum for UP, DOWN, LEFT, RIGHT
 │    └── TileFactory.java       # Creates and places random tiles
 ├── view/
 │    └── TerminalView.java      # Console board rendering (with colors)
 └── Main.java                   # Entry point of the game
```

---

## 🚀 How to Run
### 1. Compile
```bash
javac -d out src/**/*.java
```

### 2. Run
```bash
java -cp out Main
```

---

## 🎮 How to Play
- The game starts with two tiles placed randomly on a **4×4** grid.
- Use **W, A, S, D** (or arrow keys, depending on your setup) to move tiles:
  - **W** → Move Up
  - **S** → Move Down
  - **A** → Move Left
  - **D** → Move Right
- Combine tiles of the same number to add their values.
- Reach **2048** to win!
- Game ends when no moves are possible.

---

## 🖼 Sample Output
When running, the game updates the same board in the console:

```
-------------------------------------
|     2 |     4 |       |       |
-------------------------------------
|       |     2 |       |       |
-------------------------------------
|       |       |       |       |
-------------------------------------
|       |       |       |       |
-------------------------------------

Your move (W/A/S/D):
```

After a few moves, tiles merge and colors help distinguish them:

```
-------------------------------------
|  [37m2[0m   |  [36m4[0m   |       |       |
-------------------------------------
|       |  [34m8[0m   |       |       |
-------------------------------------
|       |       |  [32m16[0m  |       |
-------------------------------------
|       |       |       |       |
-------------------------------------
```

(Colors will display properly in your terminal.)

---

## 🏆 Winning & Game Over
- **You win!** → When a tile with value `2048` appears.
- **Game over** → When no empty cells remain and no adjacent tiles can be merged.

---

## 📝 Notes
- This game is designed for the console and uses **ANSI escape codes** for colors.
- Works best on terminals that support ANSI (Linux, macOS, most modern Windows terminals).

---