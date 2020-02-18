package com.bach.dv.basemvp.network.base.rx;

import com.bach.dv.basemvp.network.base.remote.response.BaseException;
import com.bach.dv.basemvp.network.cached.APISharedPrefUtils;
import com.bach.dv.basemvp.network.model.TryRequest;
import com.bach.dv.basemvp.ui.base.BaseView;
import com.bach.dv.basemvp.ui.common.Constants;
import com.bach.dv.basemvp.util.GsonHelper;
import com.bach.dv.basemvp.util.SharedPrefsUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;

public abstract class R02Subscribe<T> extends DisposableObserver<T> {

    private String cached;
    private T object;

    //BaseView is just a reference of a View in MVP
    private WeakReference<BaseView> weakReference;
    private TryRequest tryRequest;
    private String keyCached;
    private T objectCached;
    private boolean loadFirst;
    private boolean skipResponseServer;

    public R02Subscribe() {
        super();
        tryRequest = new TryRequest();
    }

    public R02Subscribe(String keyCached) {
        super();
        checkToCached(keyCached, true);
    }

    public R02Subscribe(String keyCached, boolean loadFirst) {
        super();
        this.loadFirst = loadFirst;
        checkToCached(keyCached, loadFirst);
    }

    public R02Subscribe(String keyCached, boolean loadFirst, boolean skipResponseServer) {
        super();
        this.loadFirst = loadFirst;
        this.skipResponseServer = skipResponseServer;
        checkToCached(keyCached, loadFirst);
    }

    public R02Subscribe(BaseView view) {
        this.weakReference = new WeakReference<>(view);
        this.tryRequest = new TryRequest();
    }


    public R02Subscribe(BaseView view, String keyCached) {
        checkToCached(keyCached, true);
        this.weakReference = new WeakReference<>(view);
        this.tryRequest = new TryRequest();
    }

    public R02Subscribe(TryRequest tryRequest) {
        this.tryRequest = tryRequest;
    }

    /**
     * Khởi tạo xem có xử lý cached request hay không
     *
     * @param keyCached
     * @param loadFirst true thì sẽ load từ dữ liệu cũ nếu có/false thì chỉ check sau khi load api if load false thì lấy cached
     */
    public void checkToCached(String keyCached, boolean loadFirst) {
        this.keyCached = keyCached;
        tryRequest = new TryRequest();
        try {
            if (loadFirst) {
                onAfterGetCache();
            }
            cached = APISharedPrefUtils.getStringPreference();
            if (cached != null && getType() != null) {
                objectCached = GsonHelper.getInstance().fromJson(cached, getType());
                if (loadFirst) {
                    onSuccess(objectCached, true);
                    onSuccess(objectCached);

                }
            } else {
                onFirstTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Type getType() {
        return null;
    }

    @Override
    public void onComplete() {
        onRequestFinish();
        Compare compare = new Compare().invoke();
        if (skipResponseServer) {
            if (objectCached == null) {
                onSuccess(object);
                onSuccess(object, false);
            }
            if (keyCached != null) {
                if (!compare.isSame) {
                    updateMyChannelCache();
                    APISharedPrefsUtils.setStringPreference(keyCached, compare.responseNew);
                }
            }
        } else {
            if (compare.isSame) {
                if (!loadFirst) {
                    onSuccess(object);
                    onSuccess(object, false);
                }
            } else {
                if (keyCached != null) {
                    updateMyChannelCache();
                }
                onSuccess(object);
                onSuccess(object, false);
                if (keyCached != null) {
                    APISharedPrefsUtils.setStringPreference(keyCached, compare.responseNew);
                }
            }
        }

    }

    private void updateMyChannelCache() {
        try {
            String keyAccountUpdate = "getMyChannel" + 0;
            if (keyCached.equalsIgnoreCase("getMyAccount") || keyCached.equalsIgnoreCase(keyAccountUpdate)) {
                if (object instanceof DataChannel) {
                    DataChannel dataChannelResponse = ((DataChannel) object);
                    if (keyCached.equalsIgnoreCase("getMyAccount")) {
                        try {
                            String channelCached = APISharedPrefsUtils.getStringPreference(keyAccountUpdate);
                            if (channelCached != null) {
                                DataChannel dataChannel = GsonHelper.getInstance().fromJson(channelCached, DataChannel.class);
                                if (dataChannel != null) {
                                    dataChannel.setUser(dataChannelResponse.getUser());
                                    APISharedPrefsUtils.setStringPreference(keyAccountUpdate, GsonHelper.getInstance().toJson(dataChannel));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        UserModel myAccount = MyAccountCache.getInstance().getMyAccount();
                        myAccount.setAvatarUrl(dataChannelResponse.getUser().getAvatarUrlFull());
                        MyAccountCache.getInstance().cacheUserModel(myAccount);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SharedPrefsUtils.setStringPreference(Constants.SharePref.KEY_AVATAR_USER, dataChannelResponse.getUser().getAvatarUrlFull());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        Logger.d("R02Subscribe onError");
        onRequestFinish();
        BaseView view = null;
        if (weakReference != null) {
            view = weakReference.get();
        }
        if (e != null) {
            if ((e instanceof SocketTimeoutException || e instanceof ConnectException) && view != null) {
                handleNetworkError(e, view);
                pushError(e);
                return;
            }
        }

        pushError(e);
    }

    private synchronized void pushError(Throwable e) {
        BaseException exception;
        if (e instanceof BaseException) {
            exception = (BaseException) e;
        } else {
            exception = BaseException.toUnexpectedError(e);
        }
        if (isLogout(exception)) {
            Logger.d("TOKEN" + "auto logout: " + exception.getServerErrorCode());
            if (tryRequest != null) {
                tryRequest.logout(this, exception);
            }

            return;
        }
//        if (objectCached == null) {
        onError(exception);
//        }
        if (!skipResponseServer) {
            onErrorWithCached(objectCached);
        }
    }

    private void handleNetworkError(Throwable e, BaseView view) {
        if (view != null) {
            view.showErrorScreen();
        }
    }

    public TryRequest getTryRequest() {
        return tryRequest;
    }

    private synchronized boolean isLogout(BaseException exception) {
        if (exception != null) {
            String errorCodeServer = exception.getServerErrorCode();

            return Constants.TOKEN_TIMEOUT.equals(errorCodeServer)
                    || Constants.TOKEN_INVALID.equals(errorCodeServer);
        }

        return false;
    }

    @Override
    public void onNext(T t) {
        object = t;
    }

    public abstract void onSuccess(T object);

    public void onSuccess(T object, boolean isCache) {

    }

    public void onRetry() {

    }

    public abstract void onError(BaseException error);

    public void onErrorWithCached(T objectCached) {

    }

    public void onAfterGetCache() {

    }

    /**
     * Runs after request complete or error
     **/
    public void onRequestFinish() {

    }

    /**
     * Runs after request complete or error
     **/
    public void onFirstTime() {

    }

    public class Compare {
        String responseNew;
        boolean isSame;

        public Compare invoke() {
            try {
                responseNew = GsonHelper.getInstance().toJson(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
            isSame = cached != null && cached.equals(responseNew);
            return this;
        }
    }
}
