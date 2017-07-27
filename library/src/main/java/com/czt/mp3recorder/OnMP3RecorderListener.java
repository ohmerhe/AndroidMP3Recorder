package com.czt.mp3recorder;

import java.io.File;

/**
 * Created by ohmer on 7/27/17.
 */

public interface OnMP3RecorderListener {
    void onVolumeRecord(int volume);

    void onStartRecord();

    void onCancelRecord();

    void onStopRecord(File audioFile);

    void onErrorRecord(String msg);
}
