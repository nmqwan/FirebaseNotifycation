package vn.com.winta.quan.pushnotifyfb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import vn.com.winta.quan.pushnotifyfb.Presenter.InsertToken;

public class MainActivity extends AppCompatActivity {
    String TAG= MainActivity.class.getSimpleName();
    InsertToken iToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        String token= FirebaseInstanceId.getInstance().getToken();

        iToken=new InsertToken(this);

        Log.e(TAG, "onCreate: "+token );
        iToken.insertT(token,"name");
    }
}
