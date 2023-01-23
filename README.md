# Student Project - Group 6

The Jira instace is https://cs-425-student-project.atlassian.net

This is the Student Project of Group 6 : House Rental Management System.

## Running the system

### Running with Visual Studio code

Checkout the Repository to your local machine.
Open Visual Studio code and open the git repository folder.
Install recommended extensions from Visual Studio Code ```  View -> Command Palette.. -> Run "EXtensions: Show recommended extensions"```
Modify ```src/main/resources/application.properties``` file with the values for your mysql installation.
Go to the menu Run and select "Start Debugging" or "Run without Debugging"

### Running with command line 

Bash ``` ./mvnw spring-boot:run ```
Windows Command Prompt ``` mvnw.cmd spring-boot:run ```
 ## Guidelines

* Use documents folder to storing documents. Do not commit binary files.
  * For text files, use markdown (.md) files. Use a markdown editor for updating .md files.
  * For diagrams use draw.io diagrams.

## Folder Structure

```
└───documents                         - Documents Root
    ├───diagrams                      - Diagram Files
    └───requirements                  - Requirements Files
```

## Problem Statement

[ProblemStatement.md](requirements/ProblemStatement.md)

## Use Case Diagram

<img src=documents/diagrams/UseCaseDiagram.svg/>

## Context Diagram

<img src=documents/diagrams/ContextDiagram.svg/>
