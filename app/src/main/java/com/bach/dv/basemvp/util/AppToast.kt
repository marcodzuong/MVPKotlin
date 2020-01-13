package com.bach.dv.basemvp.util

import android.content.Context
import es.dmoral.toasty.Toasty

fun Context.toastSuccess(content: String) {
    Toasty.success(this, content, Toasty.LENGTH_SHORT).show()
}

fun Context.toastError(content: String) {
    Toasty.error(this, content, Toasty.LENGTH_SHORT).show()
}

fun Context.toastWarning(content: String) {
    Toasty.warning(this, content, Toasty.LENGTH_SHORT).show()
}

fun Context.toastNormal(content: String) {
    Toasty.normal(this, content, Toasty.LENGTH_SHORT).show()
}