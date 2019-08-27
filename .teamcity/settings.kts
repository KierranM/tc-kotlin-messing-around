import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import java.time.LocalDate
import java.time.Month

version = "2019.1"

project {
    buildType(ChristmasBuild)

    buildType(SayHello("Jessie"))
    buildType(SayHello("Ash"))
    buildType(SayHello("Sam"))

    val people = listOf(
            "Julie",
            "Jessup",
            "Jackson",
            "John"
    )
    for (person in people) {
        buildType(SayHello(person))
    }
}

open class SayHello(val person: String) : BuildType({
    name = "Say Hello to $person"
    id("Hello $person".toId())

    steps {
        script {
            name = "Hello $person"
            scriptContent = "echo Hello $person"
        }
    }
})

object ChristmasBuild : BuildType({
    name = "Hello World"

    steps {
        val date = LocalDate.now()
        if (date.month == Month.DECEMBER && date.dayOfMonth == 25) {
            script {
                name = "Merry Christmas"
                scriptContent = """
                    echo "MERRY CHRISTMAS!!"
                """.trimIndent()
            }
        }
        script {
            name = "Hello World"
            scriptContent = """echo "hello world! I'm a build""""
        }
    }
})
