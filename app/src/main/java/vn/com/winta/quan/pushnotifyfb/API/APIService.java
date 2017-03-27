package vn.com.winta.quan.pushnotifyfb.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.winta.quan.pushnotifyfb.Presenter.InsertTokenImp;

/**
 * Created by QWAN on 17/03/2017.
 */

public class APIService {
    APIServiceImp api;

    public APIService() {
        this.api = APIServiceImp.retrofit.create(APIServiceImp.class);
    }

    public void insertT(final InsertTokenImp presenter, String... a) {
        Call<Void> call = api.insertToken(a[0], a[1]);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    presenter.onSuccess();
                } else presenter.onFail("onResponse");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                presenter.onFail(t.toString());
            }
        });
    }
}
