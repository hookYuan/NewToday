package lovcreate.com.newtoday.adapter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import lovcreate.com.newtoday.R;
import lovcreate.com.newtoday.bean.MediaBean;

/**
 * 作者：yuanYe创建于2016/9/23
 * QQ：962851730
 */

public class MediaAdapter extends BaseAdapter {

    List<MediaBean> data;
    private MediaPlayer mediaPlayer1;
    SurfaceView surfaceView;
    private int postion = 0;
    private boolean isFirst = true;

    public MediaAdapter(List<MediaBean> data) {
        this.data = data;
        mediaPlayer1 = new MediaPlayer();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media_activity, null);
            holder.surfaceView1 = (SurfaceView) convertView.findViewById(R.id.surfaceView);
            holder.is_play = (ImageView) convertView.findViewById(R.id.tv_3);
            surfaceView = holder.surfaceView1;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            surfaceView = holder.surfaceView1;
        }
        surfaceView.getHolder().setKeepScreenOn(true);
        surfaceView.getHolder().addCallback(new SurfaceViewLis());
        holder.is_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置播放时打开屏幕
                Log.i("yuanye","2222222222222222222-----------");
                if (mediaPlayer1.isPlaying()) {
                    mediaPlayer1.pause();
                    holder.is_play.setImageResource(R.drawable.new_play_video);
                } else {
                    mediaPlayer1.start();
                    holder.is_play.setImageResource(R.drawable.new_pause_video_press);
                }
            }
        });
        return convertView;
    }

    /**
     *
     * @param path  "/mnt/sdcard/1.mp4"
     * @throws IllegalArgumentException
     * @throws SecurityException
     * @throws IllegalStateException
     * @throws IOException
     */
    public void play(String path) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
        mediaPlayer1.reset();
        mediaPlayer1.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer1.setDataSource(path);
        // 把视频输出到SurfaceView上
        mediaPlayer1.setDisplay(surfaceView.getHolder());
        mediaPlayer1.prepare();
        mediaPlayer1.start();
        Log.i("yuanye", "zhun bei bo fang ");
    }

    private class SurfaceViewLis implements SurfaceHolder.Callback {
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            Log.i("yuanye","position="+postion);
            if (postion == 0) {
                try {
                    if(isFirst){
                        play("/mnt/sdcard/2.mp4");
                    }
                    isFirst = false;
                    mediaPlayer1.pause();
                    mediaPlayer1.seekTo(postion);
                } catch (IllegalArgumentException e) {
                    Log.e("yuanye", e.toString());
                    e.printStackTrace();
                } catch (SecurityException e) {
                    Log.e("yuanye", "2" + e.toString());
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    Log.e("yuanye", "3" + e.toString());
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("yuanye", "4" + e.toString());
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

    }

    class ViewHolder {
        TextView title, tv_headName, tv_total_paly;
        ImageView is_play, iv_head;
        SurfaceView surfaceView1;

    }
}
