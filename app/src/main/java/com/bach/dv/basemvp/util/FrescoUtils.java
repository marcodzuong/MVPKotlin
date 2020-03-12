package com.bach.dv.basemvp.util;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;

import jp.wasabeef.fresco.processors.BlurPostprocessor;
import jp.wasabeef.fresco.processors.GrayscalePostprocessor;

public class FrescoUtils {

    public static void loadImageUrl(SimpleDraweeView mSimpleDraweeView, Uri uri, int width, int height) {
        try {
            if (uri != null) {
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setResizeOptions(new ResizeOptions(ViewUtil.dpToPx(width), ViewUtil.dpToPx(height)))
                        .setProgressiveRenderingEnabled(true)
                        .build();
                mSimpleDraweeView.setController(
                        Fresco.newDraweeControllerBuilder()
                                .setOldController(mSimpleDraweeView.getController())
                                .setImageRequest(request)
                                .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadImageUrl(SimpleDraweeView mSimpleDraweeView, String url, int width, int height) {
        if (url == null) {
            return;
        }
        loadImageUrl(mSimpleDraweeView, Uri.parse(url), width, height);
    }

    public static void loadImageUrl(SimpleDraweeView mSimpleDraweeView, String url) {
        if (url == null) {
            return;
        }
        loadImageUrl(mSimpleDraweeView, Uri.parse(url));
    }

    public static void loadImageUrl(SimpleDraweeView mSimpleDraweeView, Uri uri) {
        try {
            if (uri == null) {
                return;
            }
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .build();
            mSimpleDraweeView.setController(
                    Fresco.newDraweeControllerBuilder()
                            .setOldController(mSimpleDraweeView.getController())
                            .setImageRequest(request)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void blur(Context context, SimpleDraweeView simpleDraweeView, String url) {
        try {
            if (url == null) {
                return;
            }
            Postprocessor postprocessor = new BlurPostprocessor(context, 20);
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                    .setPostprocessor(postprocessor)
                    .build();


            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void blur(Context context, SimpleDraweeView simpleDraweeView, String url, int blur) {
        try {
            if (url == null) {
                return;
            }
            BlurPostprocessor postprocessor = new BlurPostprocessor(context, blur);
//         BrightnessFilterPostprocessor brightnessFilterPostprocessor = new BrightnessFilterPostprocessor(context,0.5f);
//        CombinePostProcessors processor = new CombinePostProcessors.Builder()
//                .add(brightnessFilterPostprocessor)
//                .build();
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                    .setPostprocessor(postprocessor)
                    .setResizeOptions(new ResizeOptions(300, 200))
                    .build();


            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void blackwhite(SimpleDraweeView simpleDraweeView, String url) {
        try {
            if (url == null) {
                return;
            }
            Postprocessor postprocessor = new GrayscalePostprocessor();
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                    .setPostprocessor(postprocessor)
                    .build();

            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(simpleDraweeView.getController())
                    .build();
            simpleDraweeView.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
