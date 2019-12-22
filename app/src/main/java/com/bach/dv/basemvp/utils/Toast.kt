package com.bach.dv.basemvp.utils

import android.content.Context
import es.dmoral.toasty.Toasty

fun Context.toastSuccess(title :String ){
    Toasty.success(this,title, Toasty.LENGTH_SHORT).show()
}