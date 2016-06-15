package cn.mianyang.song314.okhttpretrofitrxjava;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * time: 6/15/16
 * description:
 *  OkHttp参考文档
 *  https://github.com/square/okhttp/wiki
 *
 * @author tangsong
 */
public class OkHttpTest extends BaseInstrumentationTestCase {


    //创建okHttpClient对象
    OkHttpClient mOkHttpClient;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mOkHttpClient = new OkHttpClient.Builder()
                    .build();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDemo() {

        Call call = buildCall(mOkHttpClient);
        //请求加入调度
        call.enqueue(new Callback() {
                         @Override
                         public void onFailure(Call call, IOException e) {
                            autoNotify();
                         }

                         @Override
                         public void onResponse(Call call, Response response) throws IOException {
                             String htmlStr = response.body().string();
                             Log.i("song", "htmlString " + htmlStr);

                             autoNotify();
                         }
                     }
        );

        autoWait();
    }


    public void testExecute() {
        Call call = buildCall(mOkHttpClient);
        try {
            Response response = call.execute();
            String htmlStr = response.body().string();
            Log.i("song", "htmlString by execute " + htmlStr);
        } catch (IOException e) {
            Log.e("song", "error in execute");
            e.printStackTrace();
        }

    }

    private Call buildCall(OkHttpClient mOkHttpClient) {
        //创建一个Request
        final Request request = new Request.Builder()
                .url("http://gank.io/api/data/福利/10/1")
                .build();
        //new call
        return mOkHttpClient.newCall(request);
    }

}
