package com.bach.dv.basemvp.network.base.rx

import com.bach.dv.basemvp.network.base.ApiConstant
import com.bach.dv.basemvp.network.base.remote.response.BaseErrorResponse
import com.bach.dv.basemvp.network.base.remote.response.BaseException
import com.bach.dv.basemvp.network.base.remote.response.BaseResponse
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

class SchedulerUtils {
    companion object {
        fun <T> convertDataFlatMap(): Function<BaseResponse<T>, Observable<T>> {
            return Function { response ->
                if (ApiConstant.SUCCESS_CODE != response.status) {
                    val baseErrorResponse = BaseErrorResponse()
                    response.status?.let {
                        response.message?.let { it1s ->
                            baseErrorResponse.setError(
                                it,
                                it1s
                            )
                        }
                    }
                    return@Function Observable.error(BaseException())
                }

                val data = response.data ?: return@Function Observable.empty()

                Observable.just(response.data)

            }
        }

        fun <T> applyAsyncSchedulers(): ObservableTransformer<T, T> {
            return ObservableTransformer { observable ->
                observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}
/**
 * Create a transformer to compose an Observable to subscribe on separate thread and observe it
 * in UI thread
 *
 * @param <T> Rule of Observable object
 */
//public static <T> ObservableTransformer<T, T> applyAsyncSchedulers() {
//    return new ObservableTransformer<T, T>() {
//        @Override
//        public ObservableSource<T> apply(Observable<T> observable) {
//            return observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        }
//    };
//}
//
//public static <T> Function<BaseResponse<T>, Observable<T>> convertDataFlatMap() {
//    return new Function<BaseResponse<T>, Observable<T>>() {
//        @Override
//        public Observable<T> apply(BaseResponse<T> response) {
//            if (!Constants.SUCCESS_CODE.equals(response.getStatus_code())) {
//                BaseErrorResponse baseErrorResponse = new BaseErrorResponse();
//                baseErrorResponse.setError(response.getStatus_code(),
//                    response.getStatus());
//                return Observable.error(BaseException.toServerError(baseErrorResponse));
//            }
//
//            T data = response.getData();
//            if (null == data) {
//                return Observable.empty();
//            }
//
//            return Observable.just(response.getData());
//        }
//    };
//}