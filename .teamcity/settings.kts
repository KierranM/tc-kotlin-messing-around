import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import java.time.LocalDate
import java.time.Month

version = "2019.1"

project {
    buildType(ChristmasBuild)
}

object ChristmasBuild : BuildType({
    name = "Hello World"
    steps {
        val date = LocalDate.now()
        if (date.month == Month.AUGUST && date.dayOfMonth == 27) {
            script {
                name = "Merry Christmas"
                scriptContent = """
                    echo "MERRY CHRISTMAS!!"
                """.trimIndent()
            }
        }
        script {
            name = "Hello World"
            scriptContent = """
                echo "hello world! I'm a build"
            """.trimIndent()
        }
    }
})