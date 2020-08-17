plugins {
    kotlin("jvm")
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    api(project(":reakt-core"))
    implementation("io.reactivex.rxjava3:rxjava:3.0.0-RC9")
}