# technicalTest

A CLI programme that automatically transfers funds from the customer’s savings account to their current account to minimise overdraft fees.

## Build Details
Written in Java 15 with Gradle build automation tool to configure dependencies (see `build.gradle`)

## Requirements
Gradle required to build JAR file, but this is done **automatically** when using constructing the `.jar` file in the set-up.

## Set-Up
1. If downloading as `.zip`, download the `.zip` file and unzip
2. In the new directory, run `$ ./gradlew jar` to build the executable JAR file
3. Change directory `$ cd build/libs`
4. Run the `.jar` file `$ java -jar <jar-file>`
