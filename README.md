# How to use this project

## Setup and usage instructions
- Run `./gradlew build` to download dependencies
- Accepted program argument: `English`, `Russian`
- To run with gradle: `./gradlew run --args="Russian"`
- To run with jar `java -jar build/libs/...path to your SNAPSHOT.jar`
- Example result:
``````
-----------------------------------------------------
Quote: Всякому созданию полезно не только все то, что посылается ему провидением, но и в то самое время, когда оно посылается.
Author: Марк Аврелий
----------------------------------------------------
``````

## Test instructions
- To run tests with gradle `./gradlew test`