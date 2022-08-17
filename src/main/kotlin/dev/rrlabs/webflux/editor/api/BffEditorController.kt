package dev.rrlabs.webflux.editor.api

import dev.rrlabs.webflux.editor.api.resource.EditorResource
import dev.rrlabs.webflux.editor.service.BffEditorService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class BffEditorController(@Autowired private val service: BffEditorService) : BffEditorApi {

    val log = LoggerFactory.getLogger(this.javaClass)

    override suspend fun searchEditorData(id: String): Flux<EditorResource> {
        log.info("Find editor data: {}", id)

        return this.service.searchEditorData(id)
    }
}