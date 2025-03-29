# puissance4

## Overview
puissance4 is a Java-based implementation of the classic Connect Four game. This project is structured using Maven, which helps manage dependencies and build processes.

## Project Structure
The project follows a standard Maven directory layout:

- **src/main/java**: Contains the main application code.
- **src/main/resources**: Contains resource files needed at runtime.
- **src/test/java**: Contains unit tests for the application.
- **src/test/resources**: Contains resource files needed for testing.

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
   You can run the application using the provided shell script:
   ```bash
   ./run.sh
   ```

## Usage
After running the application, follow the on-screen instructions to play the game. The game allows two players to take turns dropping their pieces into a grid, aiming to connect four pieces in a row, either horizontally, vertically, or diagonally.

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.