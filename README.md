
# FanCode ToDo Automation

## Overview
Welcome to my automation project for the FanCode city! This project aims to verify that all users of FanCode have more than half of their todo tasks completed. The main focus is on implementing a robust framework to automate API testing.

## Project Structure
The project is structured as follows:

```
├── .settings
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── fancode
│   │               ├── common         # Contains utility classes for the project
│   │               ├── report         # Contains report generation classes
│   │               └── request        # Contains classes for API request handling
│   └── test
│       ├── java
│       │   └── com
│       │       └── fancode
│       │           └── tests         # Contains test classes for automation
│       └── resources                  # Contains test resources
├── target                              # Maven build directory
│   ├── classes
│   ├── generated-sources
│   ├── generated-test-sources
│   ├── maven-status
│   ├── surefire-reports                # Test execution reports
│   ├── test-classes
│   └── test-reports
└── test-output                         # Output from test executions
```

## Getting Started

### Prerequisites
To get started with this project, ensure you have the following installed:
- Java 17
- Maven 3.9.9

### Setup
1. **Clone the repository**:
   ```bash
   git clone https://github.com/tushar074/FancodeToDoAutomation.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd FancodeAutomation
   ```

3. **Install dependencies**:
   ```bash
   mvn install
   ```

### Running Tests
To run the automated tests, execute the following command:
```bash
mvn test
```
This will compile the project and execute all the tests defined in the `src/test/java` directory.

## The Scenario
The project automates the following scenario:
- **Given** the user has todo tasks
- **And** the user belongs to the city FanCode (identified by latitude between -40 to 5 and longitude between 5 to 100)
- **Then** the user's completed task percentage should be greater than 50%

## Technologies Used
- Java 17
- Maven 3.9.9

Thank you for checking out my solution!
