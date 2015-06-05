package cn.sccl.ilife.android.ui.sample.login;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;

import org.apache.http.Header;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler.ILifeHttpJsonResponseHandler;
import cn.sccl.ilife.android.core.httpclient.ILifeRequestError;
import cn.sccl.ilife.android.core.httpclient.responsehandler.jsonhandler.ILifeJsonResponseInterface;
import cn.sccl.ilife.android.sample.login.Account;
import cn.sccl.ilife.android.sample.login.LoginService;
import cn.sccl.ilife.android.ui.ILifeActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_account)
public class AccountActivity extends ILifeActivity {
    @InjectView(R.id.account)
    TextView name;
    @Inject
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginService.cancelRequest(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            login();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 登录
     */
    private void login() {
        loginService.getAccount(new ILifeJsonResponseInterface<Account>() {
            @Override
            public void onILifeRequestSuccess(int statusCode, Header[] headers, byte[] responseBody, Account account) {
                name.setText(account.getUserName() + "/" + account.getUserId());
            }

            @Override
            public void onILifeHttpConnectingFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                String mes;
                if(error == null) {
                    mes = "错误";
                } else {
                    mes = error.getMessage();
                }
                Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_LONG).show();
            }
        });
    }
}
