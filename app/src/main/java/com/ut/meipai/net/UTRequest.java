package com.ut.meipai.net;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

/**
 * Created by 任和 on 2017/04/17 14:45
 * Function:
 * Desc:
 */
public class UTRequest {

    private UTRequest() {
    }

    private static class ManagerHolder {
        private static final UTRequest instance = new UTRequest();
    }

    public static UTRequest getInstance() {
        return ManagerHolder.instance;
    }

    /**
     * GET请求
     *
     * @param url      请求API地址
     * @param callBack 请求结果回调{@link RequestCallBack}
     */
    public void get(final String url, final RequestCallBack callBack) {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                OkHttpUtils
                        .get()
                        .url(url)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                emitter.onNext(response);
                            }
                        });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        callBack.onResponse(s);
                    }
                });
    }

    public interface RequestCallBack {
        /**
         * 请求成功
         *
         * @param response 返回的json数据
         */
        void onResponse(String response);

        /**
         * 请求出现异常，统一处理成失败
         */
        void onError();
    }
}
