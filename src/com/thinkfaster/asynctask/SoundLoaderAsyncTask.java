package com.thinkfaster.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import com.thinkfaster.util.ContextConstants;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;

import java.io.IOException;
import java.util.List;

/**
 * Created by brekol on 20.09.15.
 */
public class SoundLoaderAsyncTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "SoundLoaderAsyncTask";
    private final List<Sound> animalSoundList;
    private final Engine engine;
    private final Activity activity;

    public SoundLoaderAsyncTask(final List<Sound> animalSoundList, final Engine engine, final Activity activity) {
        this.animalSoundList = animalSoundList;

        this.engine = engine;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        SoundFactory.setAssetBasePath("mfx/animals/");

        try {
            for (int i = 0; i < ContextConstants.NUMBER_OF_SOUNDS; i++) {
                Log.v(TAG, ">> Loading sound number=" + i);
                animalSoundList.add(SoundFactory.createSoundFromAsset(engine.getSoundManager(), activity, i + ".ogg"));
            }
        } catch (IOException e) {
            Log.e(TAG, "Error during loading animal sounds", e);
        }

        return null;
    }
}
