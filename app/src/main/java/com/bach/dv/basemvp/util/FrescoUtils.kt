package com.bach.dv.basemvp.util

import android.content.Context
import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.backends.pipeline.PipelineDraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import jp.wasabeef.fresco.processors.BlurPostprocessor
import jp.wasabeef.fresco.processors.GrayscalePostprocessor

class FrescoUtils {
    fun loadImageUrl(mSimpleDraweeView: SimpleDraweeView, uri: Uri?, width: Int, height: Int) {
        try {
            if (uri != null) {
                val request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setResizeOptions(
                        ResizeOptions(
                            ViewUtil.dpToPx(width),
                            ViewUtil.dpToPx(height)
                        )
                    )
                    .setProgressiveRenderingEnabled(true)
                    .build()
                mSimpleDraweeView.setController(
                    Fresco.newDraweeControllerBuilder()
                        .setOldController(mSimpleDraweeView.getController())
                        .setImageRequest(request)
                        .build()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun loadImageUrl(mSimpleDraweeView: SimpleDraweeView, url: String?, width: Int, height: Int) {
        if (url == null) {
            return
        }
        loadImageUrl(mSimpleDraweeView, Uri.parse(url), width, height)
    }

    fun loadImageUrl(mSimpleDraweeView: SimpleDraweeView, url: String?) {
        if (url == null) {
            return
        }
        loadImageUrl(mSimpleDraweeView, Uri.parse(url))
    }

    fun loadImageUrl(mSimpleDraweeView: SimpleDraweeView, uri: Uri?) {
        try {
            if (uri == null) {
                return
            }
            val request = ImageRequestBuilder.newBuilderWithSource(uri)
                .build()
            mSimpleDraweeView.setController(
                Fresco.newDraweeControllerBuilder()
                    .setOldController(mSimpleDraweeView.getController())
                    .setImageRequest(request)
                    .build()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun blur(context: Context, simpleDraweeView: SimpleDraweeView, url: String?) {
        try {
            if (url == null) {
                return
            }
            val postprocessor = BlurPostprocessor(context, 20)
            val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setPostprocessor(postprocessor)
                .build()


            val controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(simpleDraweeView.getController())
                .build() as PipelineDraweeController
            simpleDraweeView.setController(controller)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun blur(context: Context, simpleDraweeView: SimpleDraweeView, url: String?, blur: Int) {
        try {
            if (url == null) {
                return
            }
            val postprocessor = BlurPostprocessor(context, blur)
            //         BrightnessFilterPostprocessor brightnessFilterPostprocessor = new BrightnessFilterPostprocessor(context,0.5f);
            //        CombinePostProcessors processor = new CombinePostProcessors.Builder()
            //                .add(brightnessFilterPostprocessor)
            //                .build();
            val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setPostprocessor(postprocessor)
                .setResizeOptions(ResizeOptions(300, 200))
                .build()


            val controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(simpleDraweeView.getController())
                .build() as PipelineDraweeController
            simpleDraweeView.setController(controller)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun blackwhite(simpleDraweeView: SimpleDraweeView, url: String?) {
        try {
            if (url == null) {
                return
            }
            val postprocessor = GrayscalePostprocessor()
            val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setPostprocessor(postprocessor)
                .build()

            val controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setOldController(simpleDraweeView.getController())
                .build() as PipelineDraweeController
            simpleDraweeView.setController(controller)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}