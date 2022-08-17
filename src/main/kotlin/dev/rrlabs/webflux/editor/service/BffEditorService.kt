package dev.rrlabs.webflux.editor.service

import dev.rrlabs.webflux.downstream.appearance.AppereanceGateway
import dev.rrlabs.webflux.downstream.mp.MpGateway
import dev.rrlabs.webflux.downstream.shops.ShopsGateway
import dev.rrlabs.webflux.editor.api.resource.EditorResource
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class BffEditorService(@Autowired private val appereanceGateway: AppereanceGateway,
                       @Autowired private val shopsGateway: ShopsGateway,
                       @Autowired private val mpGateway: MpGateway) {

    val log = LoggerFactory.getLogger(this.javaClass)

    suspend fun searchEditorData(id: String): Flux<EditorResource> {
        val editorRespnse = searchData(id)

        log.info("Response: {}", editorRespnse)

        return Flux.just(editorRespnse)
    }

    suspend fun searchData(id: String) : EditorResource = coroutineScope {
        val editorResource = EditorResource()

        launch {
            editorResource.appereanceRawContent = appereanceGateway.searchAppereanceData(id)
        }

        launch {
            editorResource.productsShops = shopsGateway.searchProductShops(id)
        }

        launch {
            editorResource.linksMp = mpGateway.searchLinksMp(id)
        }

        editorResource
    }

}