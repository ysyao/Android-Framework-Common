package cn.sccl.ilife.android.ui.sample.waitingflag;

import android.app.AlertDialog;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.ui.LightProgressDialog;
import cn.sccl.ilife.android.ui.ProgressActivity;

/**
 *
 * Created by yishiyao on 2015/3/16.
 */
public class ProgressSampleActivity extends ProgressActivity implements View.OnClickListener{
    private Button click2Act;
    private Button waitingSomeWhere;
    private EditText progressTitle;
    private AlertDialog progressDialogFragment;

    @Override
    protected View onCreateCustomView(LayoutInflater inflater) {
        View customLayout = inflater.inflate(R.layout.activity_progress_sample, null);
        click2Act = (Button) customLayout.findViewById(R.id.click_to_act);
        waitingSomeWhere = (Button) customLayout.findViewById(R.id.waiting_some_where);
        progressTitle = (EditText) customLayout.findViewById(R.id.progressbar_title);

        click2Act.setOnClickListener(this);
        waitingSomeWhere.setOnClickListener(this);
        return customLayout;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click_to_act:
                String title = progressTitle.getText().toString();
                if("".equals(title)) {
                    title = "请稍后";
                }
                if(progressDialogFragment == null) {
                    progressDialogFragment = LightProgressDialog.create(this, title);
                } else {
                    progressDialogFragment.setTitle(title);
                }
                progressDialogFragment.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(progressDialogFragment != null) {
                            progressDialogFragment.dismiss();
                        }
                    }
                }, 3000);
                break;
            case R.id.waiting_some_where:
                showProgress(true);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgress(false);
                    }
                }, 3000);
                break;
        }
    }
}
