package com.bach.dv.basemvp.network.base.rx

import com.bach.dv.basemvp.network.base.remote.response.BaseErrorResponse
import com.bach.dv.basemvp.network.base.remote.response.BaseException
import com.bach.dv.basemvp.network.base.remote.response.BaseResponse
import com.bach.dv.basemvp.ui.common.Constants
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * Create a transformer to compose an Observable to subscribe on separate thread and observe it
 * in UI thread
 *
 * @param <T> Rule of Observable object
 */
class SchedulerUtils {
    companion object {
        fun <T> applyAsyncSchedulers(): ObservableTransformer<T, T> {
            return ObservableTransformer<T, T> { observable ->
                observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }

        fun <T> convertDataFlatMap(): Function<BaseResponse<T>, Observable<T>> {
            return object : Function<BaseResponse<T>, Observable<T>> {
                override fun apply(response: BaseResponse<T>): Observable<T> {
                    if (Constants.SUCCESS_CODE != response.statusCode) {
                        val baseErrorResponse = BaseErrorResponse()
                        baseErrorResponse.setError(response.statusCode, response.status)
                        return Observable.error(BaseException.toServerError(baseErrorResponse))
                    }
                    val data: T? = response.data
                    return if (data == null) Observable.empty() else Observable.just(
                        data
                    )
                }
            }
        }
    }
}
