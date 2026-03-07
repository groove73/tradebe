import org.jetbrains.kotlin.gradle.dsl.JvmTarget
println("Available JvmTargets: ${JvmTarget.values().joinToString { it.name }}")
