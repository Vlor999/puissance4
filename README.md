# Puissance4

## Overview
Puissance4 is a Java-based implementation of the classic Connect Four game. The project is structured using Maven for dependency management and build processes. It includes a simple command-line interface for gameplay.

## Project Structure
The project follows a standard Maven directory layout:

```
puissance4
├── src
│   ├── main
│   │   ├── java
│   │   │   └── Main.java
│   │   └── resources
│   ├── test
│   │   ├── java
│   │   │   └── MainTest.java
│   │   └── resources
├── .github
│   └── workflows
│       └── ci.yml
├── LICENSE
├── pom.xml
├── README.md
└── run.sh
```

## Prerequisites
- Java 17 or higher
- Maven 3.8 or higher
- Git (optional, for cloning the repository)

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd puissance4
   ```

2. **Build the project**:
   Ensure you have Maven installed, then run:
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   Use the provided shell script to compile and launch the game:
   ```bash
   ./run.sh
   ```

## Usage
After running the application, follow the on-screen instructions to play the game. The game allows two players to take turns dropping their pieces into a grid, aiming to connect four pieces in a row (horizontally, vertically, or diagonally).

## Continuous Integration
This project uses GitHub Actions for continuous integration. The workflow is defined in `.github/workflows/ci.yml` and includes:
- Building the project
- Running tests

## Contributing
Contributions are welcome! If you find a bug or have an idea for an enhancement, feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.