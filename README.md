# Ray Tracer Challenge (Java) (WIP)

An incremental implementation of **Jamis Buck’s _The Ray Tracer Challenge_**  
using modern Java, Gradle, JUnit 4, and IntelliJ IDEA.

---

## Prerequisites

| Tool | Java               |
|------|--------------------|
| JDK 17+ | `openjdk 21.0.6`   |
| Gradle Wrapper | version 8.13       |
| IntelliJ IDEA | 2025.1.1 Community |

## Building & running tests

```bash
# clone and enter the repo
git clone https://github.com/umilton-git/RayTracerChallenge.git
cd RayTracerChallenge

# compile and run all unit tests
./gradlew test

# build the project
./gradlew build

# run the project (current default in main creates img.ppm with a single red dot)
./gradlew run