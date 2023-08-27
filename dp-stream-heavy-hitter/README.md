# README

## Testing based on the source code

To compile and run the project, you need to install the library [mpc4j](https://github.com/alibaba-edu/mpc4j) into your local Maven repository with the following step:

1. Clone mpc4j: `git clone https://github.com/alibaba-edu/mpc4j.git `.
2. Goto the root path of mpc4j: `cd mpc4j`.
3. Package and install: `mvn install`.

Then, you can use an IDE (e.g., IntelliJ IDEA) to import the source code and run unit tests or generate the jar file for experiements.

## Testing based on the pre-compiled JAR

We also provide a pre-compiled JAR file named `dp-stream-heavy-hitter-1.0-SNAPSHOT-jar-with-dependencies.jar` You can run experiments directly with that JAR file.

To run the project using the JAR file, execute the following command:

```
java -jar dp-stream-heavy-hitter-1.0-SNAPSHOT-jar-with-dependencies.jar config/synthetic_data.conf
```

where `synthetic_data.conf` is a configuration file describing necesssary parameters. We provide a template for the configuration file located in `config`, together with `synthetic_data.dat` loacted in `data/stream`.