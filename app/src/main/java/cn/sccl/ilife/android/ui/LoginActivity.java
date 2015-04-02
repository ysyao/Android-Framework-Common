package cn.sccl.ilife.android.ui;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.sccl.ilife.android.R;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Administrator on 2015/3/24.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity  extends ILifeActivity{
    @InjectView(R.id.root)
    private LinearLayout root;
    @InjectView(R.id.logo)
    private ImageView logo;
    @InjectView(R.id.skdl_btn)
    private Button skdl_btn;
    @InjectView(R.id.zjdl_btn)
    private Button zjdl_btn;
    @InjectView(R.id.account_edit)
    private  EditText account_edit;
    @InjectView(R.id.password_edit)
    private  EditText password_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

}
