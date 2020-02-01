package com.bach.dv.basemvp.network.model

class TryRequest {
    private var countTry = 0
//    fun logout(tr02Subscribe: R02Subscribe, exeption: BaseException) {
//        try {
//            SharedPrefsUtils..removeKey(Constants.SharePref.KEY_TOKEN)
//            tryAuthen(tr02Subscribe, exeption)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//    }
//
//
//    @Synchronized
//    private fun tryAuthen(tr02Subscribe: R02Subscribe, exeption: BaseException) {
//        Logger.d("tryAuthen called")
//        //        int countTry = tr02Subscribe.getTryRequest().countTry;
//        if (tr02Subscribe.getTryRequest().countTry === 2) {
//            //            tr02Subscribe.onError(exeption); // ->> comment because it will become loop.
//            tr02Subscribe.getTryRequest().countTry = 0
//            return
//        }
//
//        tr02Subscribe.getTryRequest().countTry++
//
//        val refreshTokenRequest = RefreshTokenRequest()
//        val refreshToken =
//            SharedPrefsUtils.getStringPreference(Constants.SharePref.KEY_REFRESH_TOKEN)
//        refreshTokenRequest.setRefreshToken(refreshToken)
//        val disposable = AuthRepository.getInstance().signInRefreshToken(refreshTokenRequest)
//            .subscribeWith(object :
//                R02Subscribe<RefreshTokenResponse>(tr02Subscribe.getTryRequest()) {
//
//                fun onSuccess(`object`: RefreshTokenResponse?) {
//                    if (`object` != null) {
//                        Logger.d("tryAuthen onSuccess")
//                        //                            tr02Subscribe.getTryRequest().countTry = 0;
//                        val token = `object`!!.getToken()
//                        SharedPrefsUtils.setStringPreference(Constants.SharePref.KEY_TOKEN, token)
//                        SharedPrefsUtils.setStringPreference(
//                            Constants.SharePref.KEY_REFRESH_TOKEN,
//                            `object`!!.getRefreshToken()
//                        )
//                        tr02Subscribe.onRetry()
//                    }
//                }
//
//                fun onRetry() {
//                    Logger.d("tryAuthen onRetry")
//                    tr02Subscribe.onRetry()
//                }
//
//                fun onError(error: BaseException) {
//                    Logger.d("tryAuthen onError")
//                    AppToast.createToast(ToastStyle.WARNING).setText(R.string.txt_alert_session)
//                        .show(App.getAppContext())
//                    Common.clearData()
//                    Common.gotoLogin(Utils.getApp())
//                    val eventMainActivity = EventMainActivity()
//                    eventMainActivity.setAction(0)
//                    EventBus.getDefault().post(eventMainActivity)
//                }
//            })
//
//        DisposableManager.add(disposable)
//    }
}