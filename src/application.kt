package fans.ktor.example

import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.Routing
import io.ktor.routing.get
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.DevelopmentEngine.main(args)

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {
            var html = "<li><a href = 'hello'>hello</a></li>"
            html += "<li><a href = 'now'>now</a></li>"
            call.respondText(html, ContentType.Text.Html)
        }

        get("/hello") {
            call.respondText("Hello, Ktor !", ContentType.Text.Html)
        }

        get("/now") {
            call.respondText("Now time is : ${Date()}", ContentType.Text.Html)
        }
    }
}

