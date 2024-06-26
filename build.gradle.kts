import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    id("java")
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("xyz.jpenilla.run-paper") version "2.3.0"
}

group = "de.theshadowsdust"
version = "1.2.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.opencollab.dev/main/")
}

dependencies {
    compileOnly("org.geysermc.geyser:api:2.2.0-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    implementation("cloud.commandframework", "cloud-paper", "1.8.4")
    implementation("cloud.commandframework", "cloud-annotations", "1.8.4")
    implementation("cloud.commandframework", "cloud-minecraft-extras", "1.8.4")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("me.lucko:commodore:2.2") {
        isTransitive = false
    }
}

tasks {

    compileJava {
        options.release.set(21)
        options.encoding = "UTF-8"
    }

    runServer {
        minecraftVersion("1.20.4")
    }

    shadowJar {
        archiveFileName.set("${rootProject.name}.${archiveExtension.getOrElse("jar")}")
    }
}

paper {

    name = rootProject.name
    author = "theShadowsDust"
    main = "de.theshadowsdust.ultimatespawn.UltimateSpawnPlugin"
    hasOpenClassloader = false

    generateLibrariesJson = false
    foliaSupported = false
    apiVersion = "1.20"
    bootstrapDependencies {
        register("Geyser-Spigot") {
            required = false
            load = PaperPluginDescription.RelativeLoadOrder.AFTER
        }
    }
    serverDependencies {
        register("Geyser-Spigot") {
            required = false
        }
    }
}

