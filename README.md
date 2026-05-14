# Hybrid Student Management System JavaFX DL4J

AI-enhanced student management and educational analytics system integrating JavaFX, Maven, and DeepLearning4J (DL4J).

---

# Project Overview

This project extends a traditional Java-based Student Management System into a modern JavaFX desktop application integrated with educational analytics and deep learning capabilities.

The system combines:
- object-oriented software engineering,
- desktop GUI development,
- educational performance analytics,
- and foundational deep learning integration using DL4J.

The application supports student record management while demonstrating how machine learning workflows can be integrated into educational software systems.

---

# Features

## Student Management
- Add students
- View student records
- Remove students
- GPA management
- Course management

## JavaFX Desktop Interface
- Sidebar navigation dashboard
- Responsive GUI panels
- Modern JavaFX styling
- User-friendly desktop workflow

## Deep Learning Integration
- DL4J neural network training
- Dataset loading from CSV
- Student performance classification
- Training metrics display
- Educational analytics processing

## Educational Analytics
- Student performance monitoring
- Weak vs strong performance classification
- Accuracy, precision, recall, and F1 score reporting

---

# Technologies Used

- Java 17
- JavaFX
- Maven
- DeepLearning4J (DL4J)
- ND4J
- Object-Oriented Programming (OOP)
- Educational Analytics
- Machine Learning Fundamentals

---

# Application Screenshots

## Home Dashboard

![Home Screen](screenshots/home_screen.png)

---

## Add Student Interface

![Add Student](screenshots/add_student_screen.png)

---

## View Students

![View Students](screenshots/view_students_screen.png)

---

## Remove Student

![Remove Student](screenshots/remove_student_screen.png)

---

## Deep Learning Processing

![Deep Learning Processing](screenshots/deep_learning_processing_screen.png)

---

## Trained Student Model Results

![Trained Student Model](screenshots/trained_student_model.png)

---

# Project Structure

```text
hybrid-student-management-system-javafx-dl4j/
│
├── docs/
├── screenshots/
├── src/
│   └── main/
│       ├── java/
│       └── resources/
├── .mvn/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

# How to Run

## Clone Repository

```bash
git clone https://github.com/ThereseK02/hybrid-student-management-system-javafx-dl4j.git
```

## Navigate Into Project

```bash
cd hybrid-student-management-system-javafx-dl4j
```

## Run Using Maven

```bash
mvn clean compile
mvn javafx:run
```

---

# Deep Learning Workflow

The project integrates DL4J to demonstrate a foundational educational machine learning workflow.

The application:
- loads student datasets from CSV files,
- preprocesses data,
- trains a neural network model,
- evaluates prediction metrics,
- and displays educational analytics results inside the JavaFX interface.

---

# Future Improvements

- Database integration
- Role-based authentication
- Enhanced analytics dashboards
- Larger datasets for training
- Improved model accuracy
- Visualization charts
- REST API integration

---

# Author

Therese Kabayanja

Machine Learning Engineer | Data Scientist | Software Engineer
