
https://drive.google.com/drive/folders/1pi5fZs7OffVOxdrhzO3-WoA-nIO56SZJ

# shva-settlement
shva-settlement - infrastructure for settlement product

Instructions:
1. download and copy it (unzip) to  folder (project directory)

2. compile the project with this command:
cd <project directory>
mvn clean install -Dmaven.test.skip=true

3. in order to run:

cd <project directory>
java -cp . -jar <project>\target\settlement-0.0.1-SNAPSHOT.jar

spring boot will load inside the jar, the schedule will start to run.

Folder structure describe here:

├───src
│   ├───main
│   │   ├───java
│   │   │   ├───com
│   │   │   │   └───shva
│   │   │   │       └───settlement (main application)
│   │   │   │           ├───controller (rest API)
│   │   │   │           ├───converter (java converters for covert dto to entity and opposite)
│   │   │   │           ├───dto (pojo files)
│   │   │   │           ├───exception (custom exception)
│   │   │   │           ├───model (entities)
│   │   │   │           ├───predicate 
│   │   │   │           ├───repository (DB queries)
│   │   │   │           ├───service (BL Servies)
│   │   │   │           ├───task (Schedulers)
│   │   │   │           └───util 
│   │   │   └───model
│   │   └───resources (sql db implementation)
│   └───test
│       └───java
│           └───com
│               └───shva
│                   └───settlement (junit Test Files)
└───target
    ├───classes
    │   ├───com
    │   │   └───shva
    │   │       └───settlement
    │   │           ├───controller
    │   │           ├───converter
    │   │           ├───dto
    │   │           ├───exception
    │   │           ├───model
    │   │           ├───predicate
    │   │           ├───repository
    │   │           ├───service
    │   │           ├───task
    │   │           └───util
    │   └───model
    ├───generated-sources
    │   └───annotations
    ├───lib (contains all the jar files needed)
    ├───maven-archiver
    ├───maven-status
    │   └───maven-compiler-plugin
    │       └───compile
    │           └───default-compile
    └───test-classes
        └───com
            └───shva
                └───settlement



