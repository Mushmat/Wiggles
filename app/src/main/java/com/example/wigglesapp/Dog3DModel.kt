package com.example.wigglesapp

import com.google.ar.sceneform.Scene
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.SceneView
import com.google.ar.sceneform.rendering.ModelRenderable


@Composable
fun Dog3DModel() {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            SceneView(ctx).apply {
                scene = Scene(this)

                val renderableFuture = ModelRenderable.builder()
                    .setSource(context, Uri.parse("dog.glb"))
                    .build()

                renderableFuture.thenAccept { renderable ->
                    val node = Node().apply {
                        this.renderable = renderable
                        setParent(scene)
                    }
                    scene.addChild(node)
                }
            }
        },
        update = {}
    )
}