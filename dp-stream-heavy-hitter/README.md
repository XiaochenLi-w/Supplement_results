# README

## Testing in IntelliJ IDEA

To run the project in IntelliJ IDEA, follow these steps:

1. Install the "mpc4j" library into Maven by performing the following:
   - Click on "Get from VCS."
   - Add the repository URL: https://github.com/alibaba-edu/mpc4j.git.
2. Once you have installed the "mpc4j" library, you can execute all the tests in the project.

## Testing using the JAR file

You can use the provided JAR file named "dp-stream-heavy-hitter-1.0-SNAPSHOT-jar-with-dependencies.jar" along with the configuration file located in the "config" folder.

To run the project using the JAR file, execute the following command:

```
java -jar dp-stream-heavy-hitter-1.0-SNAPSHOT-jar-with-dependencies.jar config/synthetic_data.conf
```

Make sure you have the necessary configuration file, and this command will start the application.