plugins {
    id("java")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jsoup:jsoup:1.16.1")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("org.hibernate:hibernate-core:6.5.2.Final")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation( "org.apache.logging.log4j:log4j-slf4j-impl:2.17.2" )
    implementation ("org.apache.logging.log4j:log4j-core:2.17.2")
    implementation("org.postgresql:postgresql:42.7.5")

}

tasks.test {
    useJUnitPlatform()
}