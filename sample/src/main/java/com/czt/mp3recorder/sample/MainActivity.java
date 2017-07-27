
package com.czt.mp3recorder.sample;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.czt.mp3recorder.MP3Recorder;

public class MainActivity extends Activity {
	private File mRecordFile = new File(Environment.getExternalStorageDirectory(),"test.mp3");
	private MediaPlayer mMediaPlayer;
	private MP3Recorder mRecorder = new MP3Recorder(mRecordFile);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button startButton = (Button) findViewById(R.id.StartButton);
		startButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					mRecorder.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		Button stopButton = (Button) findViewById(R.id.StopButton);
		stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mRecorder.stop();
			}
		});
		Button playButton = (Button) findViewById(R.id.PlayButton);
		playButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mRecordFile.exists()){
					try {
						mMediaPlayer = MediaPlayer.create(MainActivity.this, Uri.fromFile(mRecordFile));
						mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
							@Override
							public void onPrepared(MediaPlayer mp) {
								mMediaPlayer.start();
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					Toast.makeText(MainActivity.this, "录音文件不存在", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mRecorder.stop();
	}
}
