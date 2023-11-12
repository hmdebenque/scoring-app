# scoring-app

This application count tennis scores.

## Application goal

This Kata goal is to implement a simple tennis score computer.

The scoring system consist in one game, divided by points :

    Each player starts a game with 0 point.
    If the player wins the 1st ball, he will have 15 points. 2nd balls won : 30 points. 3rd ball won : 40points.
    If a player have 40 points and wins the ball, he wins the game, however there are special rules.
    If both players have 40 points the players are “deuce”.
    If the game is in deuce, the winner of the ball will have advantage
    If the player with advantage wins the ball he wins the game
    If the player without advantage wins the ball they are back at “deuce”.

You can found more details about the rules here : (http://en.wikipedia.org/wiki/Tennis#Scoring )

Here we want you to develop a java method that will take a String as input containing the character ‘A’ or ‘B’.
The character ‘A’ corresponding to “player A won the ball”, and ‘B’ corresponding to “player B won the ball”.
The java method should print the score after each won ball (for example : “Player A : 15 / Player B : 30”)
and print the winner of the game.

For example the following input “ABABAA” should print :

    “Player A : 15 / Player B : 0”
    “Player A : 15 / Player B : 15”
    “Player A : 30 / Player B : 15”
    “Player A : 30 / Player B : 30”
    “Player A : 40 / Player B : 30”
    “Player A wins the game

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/scoring-app-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.
