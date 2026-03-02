plugins {
    id("java")
    id("org.checkerframework").version("0.6.61")
}

group = "emwhyware-assertion"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.11.+")
}

tasks.test {
    useTestNG()
}