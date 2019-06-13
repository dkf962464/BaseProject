package com.rocky.newringtones.base;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.rocky.googleadview.googleadview.LogUtils;

import java.io.IOException;

public class BasePlayService extends Service {
    private MediaPlayer player;
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        LogUtils.e("mediaplayer is Ready");

    }

    public class MyBinder extends Binder {

        public boolean isPlaying() {
            return player.isPlaying();
        }

        public void playnetWorkMusic(String playUrl) {
            if ("".equals(playUrl) || null == playUrl) {
//                LogUtils.e("Invalid play address");
                return;
            }
            try {
                LogUtils.e("urlplay\t"+playUrl);
                player.reset();//重新设置播放器状态，如果不加这行代码则切换第二首音乐的时候出现闪退
                player.setDataSource(playUrl);
                player.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != player) {
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        player.start();
                    }
                });
            }
        }

        public void playLocationMusic(String playUrl) {
            try {
                player.setDataSource(playUrl);
                player.prepare();
                if (player.isPlaying()) {
                    player.pause();
                } else {
                    player.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            LogUtils.e("start play music");
        }

        public int getDuration() {
            return player.getDuration();
        }

        public int getCurrenPostion() {
            return player.getCurrentPosition();
        }

        public void seekTo(int mesc) {
            player.seekTo(mesc);
        }
    }
}
