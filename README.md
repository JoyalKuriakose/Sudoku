Android User Authentication App

This is a simple Android application that demonstrates basic user authentication using SQLite as a local database. The app allows users to register, log in, and navigate to a home page after successful authentication.

Features

- User Registration
- User Login with SQLite authentication
- Home screen after successful login
- Input validation
- Simple and clean UI

Tech Stack

- **Language**: Java
- **Platform**: Android
- **Database**: SQLite (via `DatabaseHelper.java`)
- **IDE**: Android Studio

Project Structure


├── MainActivity.java          # Entry point of the app (Splash / Welcome screen)
├── LoginActivity.java         # Handles user login functionality
├── RegisterActivity.java      # Handles user registration functionality
├── HomeActivity.java          # Displays after successful login
├── DatabaseHelper.java        # SQLite DB operations (insert, validate, etc.)

Getting Started

1. Clone the repository:

   git clone https://github.com/JoyalKuriakose/Sudoku

2. Open the project in **Android Studio**.

3. Build the project and run it on an emulator or physical device.

Usage

* **Register**: Create a new account by entering your username, email, and password.
* **Login**: Use your credentials to log in.
* **Home Page**: Once logged in, you'll be redirected to the home screen.

Requirements

* Android Studio
* Android SDK 21+
* Java JDK 8+

Author

* Joyal Kuriakose
