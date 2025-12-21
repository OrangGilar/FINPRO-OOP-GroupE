# Student Runner - OOP Final Project (Group E)

**"Student Runner"** is an endless runner platformer game developed using **Java (LibGDX)** and **Spring Boot**. The game simulates the life of a university student trying to survive the semester by dodging assignments and F-grades while maintaining their GPA.

## 🎮 Game Theme & Mechanics

* **Setting:** University Campus.
* **Player:** A student equipped with a jetpack.
* **Health System (GPA):** Instead of standard HP, the player has a **GPA** ranging from **4.00 to 0.00**.
    * Hitting an **Assignment** (Ground Obstacle) drops GPA by **0.1**.
    * Hitting an **F-Grade** (Flying Obstacle) drops GPA by **0.5**.
    * If GPA hits **0.00**, the player drops out (Game Over).
* **Goal:** Survive the semester distance (e.g., 2000m or 5000m) and catch the **Diploma/Trophy** at the end.
* **Collectibles:** Coins can be collected to increase the final score.

---

## 🛠 Tech Stack

* **Frontend:** Java, LibGDX Framework (Core, Desktop).
* **Backend:** Java, Spring Boot 3.x.
* **Database:** H2 Database (File-based storage).
* **Networking:** HTTP REST API (JSON).

---

## 🏗 Design Patterns Implemented

This project demonstrates proficiency in Object-Oriented Programming by implementing the following Design Patterns:

### 1. State Pattern (`com.nama.frontend.states`)
* **Usage:** Manages the different screens of the game.
* **Implementation:** `GameStateManager` controls the flow between `MenuState`, `LevelSelectState`, `PlayingState`, `WinState`, and `GameOverState`.

### 2. Strategy Pattern (`com.nama.frontend.strategies`)
* **Usage:** Handles difficulty scaling dynamically.
* **Implementation:** `DifficultyStrategy` interface allows swapping between:
    * `EasyDifficultyStrategy` (Freshman Year): Slower speed, mostly ground obstacles.
    * `HardDifficultyStrategy` (Senior Year): Fast speed, chaotic flying obstacles.

### 3. Factory Method & Object Pool (`com.nama.frontend.obstacles`)
* **Usage:** Efficiently creates and recycles game entities to prevent memory leaks (Garbage Collection lag).
* **Implementation:**
    * `ObstacleFactory` manages pools for `Assignment` and `FGrade` objects.
    * `CoinFactory` manages the pool for `Coin` objects.
    * `BulletFactory` (if applicable) manages projectiles.

### 4. Observer Pattern (`com.nama.frontend.observers`)
* **Usage:** Decouples the UI from the Player logic.
* **Implementation:** The `Player` entity acts as the Subject, notifying the `HealthBar` (Observer) whenever GPA changes. The UI updates automatically without the Player class knowing about UI rendering.

### 5. Singleton Pattern (`com.nama.frontend.managers`)
* **Usage:** Ensures only one instance handles global game data.
* **Implementation:** `GameDataManager` is a Singleton that manages the Player ID, Username, and connection settings across all states.

### 6. Service Layer (`com.nama.frontend.services`)
* **Usage:** Separates networking logic from game logic.
* **Implementation:** `BackendService` acts as a bridge to communicate with the Spring Boot API.

---

## 🚀 How to Run the Project

This project requires two separate applications running simultaneously: the **Backend Server** and the **Frontend Game**.

### Step 2: Run the (Game)
1.  Open the `libgdx` (root) folder in IntelliJ IDEA / Android Studio.
2.  Run the `DesktopLauncher` configuration.
3.  **Registration:** If it's your first time playing, the game will ask for a **Username**.
4.  **Menu:**
    * Press **ENTER** to start (Goes to Level Select).
    * Press **L** to view the Global Leaderboard (fetched from Backend).

---

## 📡 API Endpoints

The Game Client communicates with the Backend via the following REST Endpoints:

| Method | Endpoint | Description | Payload Example |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/players` | Registers a new student | `{"username": "Alice"}` |
| `POST` | `/api/scores` | Submits final semester stats | `{"playerId": "uuid...", "score": 5000, "coins": 20, "distance": 100}` |
| `GET` | `/api/leaderboard` | Fetches top 10 students | N/A |
| `GET` | `/api/players` | Fetches all registered players | N/A |

---

## 👥 Contributors (Group E)

* **[Muhammad Fairuz dzaki]** - [2406368864]
* **[Muhammad Naufal gilardino]** - [2406450434]
* **[Raihan Herhumadzib]** - [2406369002]
* **[Yasa muafa siregar]** - [2406368896]
* **[Paskah rahmat abed]** - [2406450453]
---

*This project was created for the Object-Oriented Programming Final Project at Universitas Indonesia.*
