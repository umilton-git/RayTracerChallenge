# Ray Tracer Challenge (Java)

An incremental implementation of **Jamis Buck’s _The Ray Tracer Challenge_**  
using modern Java, Gradle, JUnit 4, and IntelliJ IDEA.

---

## Prerequisites

| Tool | Version tested |
|------|----------------|
| JDK 17+ | `openjdk 21.0.6` |
| Gradle Wrapper | `./gradlew --version` prints 8.x |
| IntelliJ IDEA | 2024.x Community/Ultimate |

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