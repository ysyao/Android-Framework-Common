package cn.sccl.ilife.android.ui.sample.photoview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.inject.Inject;

import org.apache.http.Header;

import java.io.File;

import cn.sccl.ilife.android.R;
import cn.sccl.ilife.android.core.httpclient.responsehandler.filehandler.ILifeHttpFileResponseHandler;
import cn.sccl.ilife.android.core.service.ProgressDialogService;
import cn.sccl.ilife.android.sample.photoview.PhotoService;
import cn.sccl.ilife.android.sample.photoview.PhotoUtils;
import cn.sccl.ilife.android.ui.ILifeActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import uk.co.senab.photoview.PhotoViewAttacher;

@ContentView(R.layout.activity_photo_view_list)
public class PhotoViewListActivity extends ILifeActivity {
    @InjectView(R.id.photoview_image)
    private ImageView image;
    @Inject
    private PhotoService photoService;
    @InjectView(R.id.image_progress)
    private ProgressBar progressBar;
    private PhotoViewAttacher mAttacher;

    private static final String IMAGE_URL = "http://www.ctv.ca/getattachment/62ebda22-fc08-47a3-8b1e-c02ffb179979/TheBigBangTheory.aspx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadImageFromRemote();
    }

    @Override
    protected void onStop() {
        super.onStop();
        photoService.cancelRequest(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_view_list, menu);
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
            downloadImageFromRemote();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void downloadImageFromRemote() {
        if(progressBar.getVisibility() == View.GONE) {
            progressBar.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
        }
        photoService.getImage(IMAGE_URL, new ILifeHttpFileResponseHandler(this) {

            @Override
            public void onILifeRequestSuccess(int statusCode, Header[] headers, File file) {
                Bitmap bitmap = PhotoUtils.transFile2Bitmap(file);
                image.setImageBitmap(bitmap);
                mAttacher = new PhotoViewAttacher(image);
                mAttacher.update();
                showImage();
            }

            @Override
            public void onILifeRequestFailed(int statusCode, Header[] headers, Throwable throwable, File file) {
                Toast.makeText(getApplicationContext(), statusCode, Toast.LENGTH_LONG).show();
                showImage();
            }

            @Override
            public void onILifeHttpConnectingFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), statusCode, Toast.LENGTH_LONG).show();
                showImage();
            }
        });
    }

    private void showImage() {
        image.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
