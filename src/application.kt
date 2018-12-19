package fans.ktor.example

import fans.ktor.example.model.IndexData
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.freemarker.FreeMarker
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.ContentType
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    routing {
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

        get("/index") {
            call.respond(FreeMarkerContent("index.ftl", mapOf("data" to IndexData(listOf(1, 2, 3))), ""))
        }
    }
}

