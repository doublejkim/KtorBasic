val exposed_version: String by project
val h2_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val mysqlVersion: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.2.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.10"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    // 기본적인 ktor 의존성
    implementation("io.ktor:ktor-server-core:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$kotlin_version")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$kotlin_version")

    //yaml 설정
    implementation("io.ktor:ktor-server-config-yaml:$kotlin_version")

    // slf4j 용 . logback-classic 은 기본으로 들어감 slf4j 는 별도로 기술
    implementation("org.slf4j:slf4j-api:2.0.17")
    implementation("ch.qos.logback:logback-classic:${logback_version}")

    // content negotiation. plugin 선택에서 선택하면 포함됨
    implementation("io.ktor:ktor-server-content-negotiation:$kotlin_version")
    implementation("io.ktor:ktor-serialization-jackson:$kotlin_version")

    // Jackson Java Time 모듈 (LocalDateTime 처리)
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.19.1")

    // Exposed (Kotlin SQL framework)
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:${exposed_version}")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:${exposed_version}")

    // mysql driver
    implementation("com.mysql:mysql-connector-j:$mysqlVersion")

    // Connection Pool
    //implementation("hikari-cp:hikari-cp:3.3.0")

    // Koin (의존성 관리)
    implementation("io.insert-koin:koin-ktor:3.5.6")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.6")

    // ktor-server-auth?

    // PASETO 의존성
    implementation("dev.paseto:jpaseto-api:0.7.0")
    implementation("dev.paseto:jpaseto-impl:0.7.0")
    implementation("dev.paseto:jpaseto-jackson:0.7.0")

    // minIO (AWS S3 와 API 형태로 호환됨. docker 로 이미지화해서 사용가능. Object Storage)
    implementation("io.minio:minio:8.5.17")


    // logging (plugin. 로깅)
    implementation("io.ktor:ktor-server-call-logging:$kotlin_version")

    // https://mvnrepository.com/artifact/jakarta.mail/jakarta.mail-api
    implementation("jakarta.mail:jakarta.mail-api:2.1.3")

    // AWS SDK for SES
    // implementtation(platfor("software.amazon.awssdk:bom:2.17.219"))
    // implementation("software.amazon.awssdk:ses")

    //implementation("com.h2database:h2:$h2_version")


    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
