package com.rocky.newringtones.home.view;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rocky.googleadview.googleadview.ExAdviewUtil;
import com.rocky.googleadview.googleadview.LogUtils;
import com.rocky.newringtones.R;
import com.rocky.newringtones.base.baseutil.BaseActivity;
import com.rocky.newringtones.base.db.query.DbInterface;
import com.rocky.newringtones.base.db.query.DbQuery;
import com.rocky.newringtones.base.db.queryimpl.DbQueryImpl;
import com.rocky.newringtones.home.model.LoginJsonBean;
import com.rocky.newringtones.home.model.TestDbModel;
import com.rocky.newringtones.home.presenter.HomePresenter;
import com.rocky.vipmicrogame.JniEncryption;

import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomePresenter, HomeActivity> implements HomeViewInterface, DbInterface {
    static {
        System.loadLibrary("test");
    }
    @BindView(R.id.play_but)
    ImageView mImageView;
    @BindView(R.id.left)
    ImageView mLeft;
    @BindView(R.id.right)
    ImageView mRight;
    private long mExitTime;
    private DbQuery<TestDbModel> mDbQuery;
    private String tablName="testDb";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.login();
        presenter.requestExView(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mDbQuery = new DbQueryImpl<>(this);
//        String sql = "create table "+tablName + "(_uuid Integer primary key,user text not null)";
//        SQLiteDatabase db = ((DbQueryImpl<TestDbModel>) mDbQuery).getDb();
//        db.execSQL(sql);
    }

    @Override
    protected void makeData() {
        String encrypt = JniEncryption.encrypt("xxhdpi");
        String decrypt = JniEncryption.decrypt(encrypt);
        Log.e("tags", "" + encrypt);
        Log.e("tags", "" + decrypt);

        mLeft.setOnClickListener(v->{
            LoginJsonBean loginJsonBean=new LoginJsonBean();
            loginJsonBean.setName("xxhdpsi");
            loginJsonBean.setPwd("123456s");
            loginJsonBean.setUuid(2);
            TestDbModel testDbModel=new TestDbModel();
            testDbModel.setLoginJsonBean(loginJsonBean);
            testDbModel.setUuid(2);
            mDbQuery.save("testDb",testDbModel,this);
        });
        mRight.setOnClickListener(v -> {
            String loginJsonBean = mDbQuery.queryById("testDb", "_uuid", 2);
            Log.e("jsonStr", "makeData: "+loginJsonBean );
            TestDbModel testDbModel = new Gson().fromJson(loginJsonBean, TestDbModel.class);
            Log.e("testDb", "makeData: "+testDbModel.getLoginJsonBean().getName());
        });
        mImageView.setOnClickListener(v -> {
            List<TestDbModel> testDb = mDbQuery.queryAll("testDb");
            for (int i = 0; i <testDb.size() ; i++) {
                Log.e("testDb", ""+testDb.get(i).getLoginJsonBean().getName());
            }
        });
    }

    //是否开启沉浸式
    @Override
    public boolean isImmersive() {
        return true;
    }

    //返回子类Presenter
    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public void resultSuccess(Object object) {
        LoginJsonBean loginJsonBean = (LoginJsonBean) object;
        Toast.makeText(this, "" + loginJsonBean.toString(), Toast.LENGTH_SHORT).show();
        LogUtils.e(loginJsonBean.getName());
    }

    @Override
    public void resultFailed(String msg) {
        LogUtils.e(msg);
    }

    @Override
    public void save(Object o, SQLiteDatabase db, String sql) {
        TestDbModel loginJsonBean= (TestDbModel) o;
        Gson gsos=new Gson();
        String json=gsos.toJson(loginJsonBean,TestDbModel.class);
        db.execSQL(sql,new Object[]{loginJsonBean.getUuid(),json});
    }

    @Override
    public void merge(Object o, String sql, SQLiteDatabase db) {

    }
}
