package com.rocky.newringtones.vview;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.rocky.newringtones.R;
import com.rocky.newringtones.base.BaseActivity;
import com.rocky.newringtones.search.view.SearchActivity;
import com.rocky.vipmicrogame.JniEncryption;
import butterknife.BindView;
public class HomeActivity extends BaseActivity {
    {
        System.loadLibrary("test");
    }
    @BindView(R.id.play_but)
    ImageView mImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        LinearLayout view=findViewById(R.id.view_);
//        BaAdviewUtil.showBaAdView(this,"ca-app-pub-8519724823288511/4211538839",view, BaSize.BANNER);
//        LogUtils.e("xxhdpi");
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, SearchActivity.class));
                }
            });

    }

    @Override
    protected int setContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        String encrypt = JniEncryption.encrypt("xxhdpi");
        String decrypt =JniEncryption.decrypt(encrypt);
        Log.e("tags", ""+encrypt);
        Log.e("tags", ""+decrypt);
    }

    @Override
    protected void initView() {

    }
    @Override
    public boolean isImmersive() {
        return false;
    }
}
