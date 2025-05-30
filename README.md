## Automation Assignment 

This is a Selenium-based Java automation testing framework using Java, TestNG, Cucumber (BDD), and 
the Page Object Model (POM) design pattern.

## Prerequisites
- Java 11 or higher
- Maven 3.6+
- IDE - IntelliJ IDEA

## Project Structure

Automation_Assignment
│
├── .idea/                         
├── logs/                          # Log files
│   └── automation.log
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── base/              # Base classes (e.g., BaseClass)
│   │   │   ├── functions/         # Common reusable functions
│   │   │   ├── pages/             # Page Object Model classes
│   │   │   └── utility/           # Utilities (DriverFactory, PropertyReader, RetryAnalyzer , ChainTestReportConfiguration)
│   │   └── resources/             # Main resources (optional)
│   └── test/
│       ├── java/
│       │   ├── runner/            # Cucumber TestRunner
│       │   ├── setup/             # Test setup 
│       │   └── stepdefinitions/   # Step definition files (Hooks)
│       └── resources/
│           ├── features/         # Cucumber .feature files
│           └── *.properties      # Test configuration and test data
├── target/                        # Compiled code & generated reports
├── test-output/                   # ChainTest Report
├── xmlPlans/                      # XML suite files for TestNG
├── pom.xml                        # Maven configuration
└── README.md                      # Project documentation 

## Tech Stack

Language: Java

Automation Tool: Selenium WebDriver

Test Framework: TestNG

BDD: Cucumber

Design Pattern: Page Object Model (POM)

Build Tool: Maven

Logging: Log4j2

Reports: Chain Test Report 

## How to Run Tests

1. Install dependencies
Run mvn clean install to install all required dependencies.

2. Execute Tests
mvn test

3. View Reports
After test execution, reports can be found at: test-output/chaintest - Email.html | Index.html

## Features

1. View users in User Management
2. Add a new user in User Management by Client Admin
3. Add a new user in User Management by Super Admin
4. Suspend a user in User Management
5. Edit a user in User Management
6. Delete a user in User Management
7. Login with a suspended account
8. Login with a deleted account

## CI/CD Integration

This project uses GitHub Actions for Continuous Integration. 
Every push or pull request to the `main` branch triggers the automated tests.

Workflow file: `.github/workflows/test.yml`

The pipeline:
- Sets up Java 11
- Builds the project using Maven
- Executes automated tests using TestNG and Cucumber
- Uploads reports as artifacts


## Contributing

1. Fork this repository
2. Create a new branch: `git checkout -b feature/your-feature-name`
3. Commit your changes: `git commit -m 'Add new feature'`
4. Push to the branch: `git push origin feature/your-feature-name`
5. Open a pull request



