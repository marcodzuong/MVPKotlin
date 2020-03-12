package com.bach.dv.basemvp.widget.toast;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bach.dv.basemvp.R;
import com.bach.dv.basemvp.util.NetworkUtils;
import com.bach.dv.basemvp.util.ViewUtil;

public class AppToast {
    private int contentResId;
    private int idImgToast;
    private int backGroundColor;
    private int colorText;
    private int timeShow = 0;
    private int textFont = 0;
    private boolean isHtml;
    private String text;
    private int duration = Toast.LENGTH_SHORT;


    public AppToast setHtml(boolean html) {
        isHtml = html;
        return this;
    }

    public AppToast setImgToast(int idImgToast) {
        this.idImgToast = idImgToast;
        return this;
    }

    public AppToast setColorTextToast(int colorText) {
        this.colorText = colorText;
        return this;
    }

    public AppToast setText(String text) {
        this.text = text;
        return this;
    }

    public AppToast setText(int contentResId) {
        this.contentResId = contentResId;
        return this;
    }

    public AppToast setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
        return this;
    }

    public static AppToast createToast(ToastStyle toastStyle) {
        switch (toastStyle) {
            case ERROR:
                return createToastError();

            case WARNING:
                return createToastWarning();
//
//            case DOWNLOADING:
//                return createToastDownloading();
//
//            case DOWNLOAD_COMPLETE:
//                return createToastDownloadComplete();
        }

        return createToastLiked();
    }

    public static void showToastNetworkError(Context context) {
        int stringErrorId;
        if (!NetworkUtils.isNetworkConnected(context)) {
            stringErrorId = R.string.network_error;
        } else {
            stringErrorId = R.string.server_error;
        }
        createToast(ToastStyle.ERROR).setText(stringErrorId).show(context);
    }

    public static void showToastNetworkErrorWithTitle(Context context, String title) {

        createToast(ToastStyle.ERROR).setText(title).show(context);
    }

    public static void showToastNetworkErrorForPending(Context context) {
        int stringErrorId;
        if (!NetworkUtils.isNetworkConnected(context)) {
            stringErrorId = R.string.network_error;
        } else {
            stringErrorId = R.string.audio_error_load;
        }
        createToast(ToastStyle.ERROR).setText(stringErrorId).show(context);
    }

//    public static void showBeginDownload(Album album, Context context) {
//        AppToast.createToast(ToastStyle.DOWNLOADING).setText(context.getString(R.string.suscribed) + " <b>" + album.getTitle() + "</b>").setHtml(true).show(context);
//    }

//    public static void showSubscribeState(Album album, Context context) {
//        if (album.isSubscribed()) {
//            AppToast.createToast(ToastStyle.DONE).setText(context.getString(R.string.suscribed) + " <b>" + album.getTitle() + "</b>").setHtml(true).show(context);
//        } else {
//            AppToast.createToast(ToastStyle.ERROR).setText(context.getString(R.string.unsuscribed) + " <b>" + album.getTitle() + "</b>").setHtml(true).show(context);
//        }
//    }

//    public static void showSubscribeChannelState(boolean isSubsUserChannel,String name, Context context) {
//        if (isSubsUserChannel) {
//            AppToast.createToast(ToastStyle.DONE).setText(context.getString(R.string.suscribed) + " <b>" + name + "</b>").setHtml(true).show(context);
//        } else {
//            AppToast.createToast(ToastStyle.ERROR).setText(context.getString(R.string.unsuscribed) + " <b>" + name + "</b>").setHtml(true).show(context);
//        }
//    }

//    public static void showBookmarkToast(Podcast detail, Context context) {
//        try {
//            if (detail.isBookmarked()) {
//                AppToast.createToast(ToastStyle.DONE).setText(context.getString(R.string.bookmarked) + " <b>" + detail.getName() + "</b>").setHtml(true)
//                        .show(context);
//            } else {
//                AppToast.createToast(ToastStyle.ERROR).setText(context.getString(R.string.unbookmarked) + " <b>" + detail.getName() + "</b>").setHtml(true)
//                        .show(context);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private static AppToast createToastLiked() {
        AppToast appToast = new AppToast();
        return appToast.setImgToast(R.drawable.ic_toast_done).setBackGroundColor(Color.parseColor("#EAFAED")).setColorTextToast(Color.parseColor("#079B20"));
    }

    private static AppToast createToastWarning() {
        AppToast appToast = new AppToast();
        return appToast.setImgToast(R.drawable.ic_toast_warning).setBackGroundColor(Color.parseColor("#FFFAE6")).setColorTextToast(Color.parseColor("#C49C00"));
    }
//
//    private static AppToast createToastDownloading() {
//        AppToast appToast = new AppToast();
//        return appToast.setImgToast(R.drawable.ic_icon_downloading).setBackGroundColor(Color.parseColor("#FFF9F9")).setColorTextToast(Color.parseColor("#000000"));
//    }
//
//    private static AppToast createToastDownloadComplete() {
//        AppToast appToast = new AppToast();
//        return appToast.setImgToast(R.drawable.ic_icon_download).setBackGroundColor(Color.parseColor("#FFF9F9")).setColorTextToast(Color.parseColor("#000000"));
//    }


    private static AppToast createToastError() {
        AppToast appToast = new AppToast();
        return appToast.setImgToast(R.drawable.ic_toast_cancel).setBackGroundColor(Color.parseColor("#FEEBE8")).setColorTextToast(Color.parseColor("#F43319"));
    }

    public void show(Context context) {
        build(context).show();
    }

    public Toast build(Context context) {
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        View view = LayoutInflater.from(context).inflate(R.layout.view_custom_toast, null);
        View customToastContainer = view.findViewById(R.id.custom_toast_container);
        Drawable background = customToastContainer.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(backGroundColor);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(backGroundColor);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background).setColor(backGroundColor);
        }
        TextView textView = view.findViewById(R.id.text);
        textView.setTextColor(colorText);
        if (contentResId != 0) {
            textView.setText(contentResId);
        } else {
            textView.setText(isHtml ? Html.fromHtml(text) : text);
        }
//        Typeface font = Typeface.create("",textFont);
//        textView.setTypeface(font);

        ImageView img = view.findViewById(R.id.img_toast);
        img.setImageResource(idImgToast);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
        toast.setView(view);
        return toast;
    }


//    public AlertDialog buildDialog(Context context, IOnTouchDialogListener onTouchListener, View... viewsCanTouch) {
//
//        AlertDialog.Builder alert = new AlertDialog.Builder(context, R.style.AppBaseTheme);
//
//        View view = LayoutInflater.from(context).inflate(R.layout.view_custom_dialog, null);
//        alert.setView(view);
//
//        AlertDialog dialog = alert.create();
//        int width = (int) (context.getResources().getDisplayMetrics().widthPixels);
//        int height = (int) (context.getResources().getDisplayMetrics().heightPixels);
//        view.setLayoutParams(new LinearLayout.LayoutParams(width, height));
//        view.findViewById(R.id.flContent).setLayoutParams(new LinearLayout.LayoutParams(width, height));
//        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialogInterface) {
//                try {
//                    View customToastContainer = view.findViewById(R.id.custom_toast_container);
//                    ViewAnimator
//                            .animate(customToastContainer)
//                            .translationY(-200, 0)
//                            .duration(300)
//                            .alpha(0, 1).start();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        View customToastContainer = view.findViewById(R.id.custom_toast_container);
////        alert.setCancelable(true);
////        dialog.setCanceledOnTouchOutside(true);
//        View btnContent = view.findViewById(R.id.flContent);
//        btnContent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                dialog.dismiss();
//                try {
//                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                        for (View view1 : viewsCanTouch) {
////                            if (view1 instanceof ViewGroup) {
////                                checkView(view1, motionEvent, onTouchListener, dialog);
////                            } else {
//                            if (view1 != null && inViewBounds(view1, motionEvent)) {
//                                Logger.d(view1.toString());
//                                view1.performClick();
//                                onTouchListener.onClicked(view1, dialog);
//                                break;
//                            }
////                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return false;
//            }
//        });
//
//        try {
//            Drawable background = customToastContainer.getBackground();
//            if (background instanceof ShapeDrawable) {
//                ((ShapeDrawable) background).getPaint().setColor(backGroundColor);
//            } else if (background instanceof GradientDrawable) {
//                ((GradientDrawable) background).setColor(backGroundColor);
//            } else if (background instanceof ColorDrawable) {
//                ((ColorDrawable) background).setColor(backGroundColor);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        try {
//            TextView textView = view.findViewById(R.id.text);
//            textView.setTextColor(colorText);
//            if (contentResId != 0) {
//                textView.setText(contentResId);
//            } else {
//                textView.setText(isHtml ? Html.fromHtml(text) : text);
//            }
//
//            ImageView img = view.findViewById(R.id.img_toast);
//            img.setImageResource(idImgToast);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return dialog;
//    }

    private void checkView(View view, MotionEvent motionEvent, IOnTouchDialogListener onTouchListener, DialogInterface dialogInterface) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View viewChild = ((ViewGroup) view).getChildAt(i);
                if (viewChild != null) {
                    if (viewChild instanceof ViewGroup) {
                        checkView(viewChild, motionEvent, onTouchListener, dialogInterface);
                    } else {
                        if (inViewBounds(viewChild, motionEvent)) {
                            viewChild.performClick();
                            onTouchListener.onClicked(viewChild, dialogInterface);
                            break;
                        }
                    }
                }

            }
        }
    }

    private boolean inViewBounds(View v, MotionEvent event) {
        int vWidth = v.getWidth();
        int vHeight = v.getHeight();

        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int lWidth = location[0];
        int lHeight = location[1] - vHeight + ViewUtil.dpToPx(15);

        if ((event.getY() - lHeight) >= 0 && (event.getY() - lHeight) <= vHeight && (event.getX() - lWidth) >= 0 && (event.getX() - lWidth) <= vWidth)
            return true;
        else return false;
    }


    public interface IOnTouchDialogListener {
        void onClicked(View view, DialogInterface dialogInterface);

    }

//    public void attachView(Context context, ViewGroup viewGroup) {
//
//        View view = LayoutInflater.from(context).inflate(R.layout.view_custom_dialog, null);
//        int width = (int) (context.getResources().getDisplayMetrics().widthPixels);
//        int height = (int) (context.getResources().getDisplayMetrics().heightPixels);
//        view.setLayoutParams(new LinearLayout.LayoutParams(width, height));
//        view.findViewById(R.id.flContent).setLayoutParams(new LinearLayout.LayoutParams(width, height));
//
//        View customToastContainer = view.findViewById(R.id.custom_toast_container);
////        alert.setCancelable(true);
////        dialog.setCanceledOnTouchOutside(true);
//        View btnContent = view.findViewById(R.id.flContent);
//        btnContent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    Logger.d("XClick_" + motionEvent.getX() + "YClick_" + motionEvent.getY());
//                }
//                return false;
//            }
//        });
//
//        Drawable background = customToastContainer.getBackground();
//        if (background instanceof ShapeDrawable) {
//            ((ShapeDrawable) background).getPaint().setColor(backGroundColor);
//        } else if (background instanceof GradientDrawable) {
//            ((GradientDrawable) background).setColor(backGroundColor);
//        } else if (background instanceof ColorDrawable) {
//            ((ColorDrawable) background).setColor(backGroundColor);
//        }
//        TextView textView = view.findViewById(R.id.text);
//        textView.setTextColor(colorText);
//        if (contentResId != 0) {
//            textView.setText(contentResId);
//        } else {
//            textView.setText(isHtml ? Html.fromHtml(text) : text);
//        }
//
//        ImageView img = view.findViewById(R.id.img_toast);
//        img.setImageResource(idImgToast);
//
////        ViewParent viewParent = viewGroup.getView().getParent();
////        if (viewParent instanceof ViewGroup) {
//        viewGroup.addView(view);
////        }
//    }

    public AppToast setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public int getDuration() {
        return duration;
    }


}
