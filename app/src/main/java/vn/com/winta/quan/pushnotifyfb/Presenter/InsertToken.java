package vn.com.winta.quan.pushnotifyfb.Presenter;

import android.content.Context;
import android.util.Log;

import vn.com.winta.quan.pushnotifyfb.API.APIService;

/**
 * Created by QWAN on 17/03/2017.
 */

public class InsertToken {
    String TAG = InsertToken.class.getSimpleName();
    APIService api;
    Context context;

    public InsertToken(Context context) {
        this.api = new APIService();
        this.context=context;
    }
    public void insertT(String token,String name){
        api.insertT(new InsertTokenImp() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess: onSuccess" );
            }

            @Override
            public void onFail(String mss) {
                Log.e(TAG, "onFail: "+mss );
            }
        },token,name);
    }
}
