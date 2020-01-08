package com.bach.dv.basemvp.util

import android.content.Context
import es.dmoral.toasty.Toasty

fun Context.toastSuccess(content: String) {
    Toasty.success(this, content, Toasty.LENGTH_SHORT).show()
}

fun Context.toastError(content: String) {
    Toasty.error(this, content, Toasty.LENGTH_LONG).show()
}

fun Context.toastWarning(content: String) {
    Toasty.warning(this, content, Toasty.LENGTH_LONG).show()
}