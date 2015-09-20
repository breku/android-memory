package com.thinkfaster.service;

import android.util.Log;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.Comparator;
import java.util.List;

import static com.google.common.collect.Iterables.limit;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.sort;

/**
 * Created by brekol on 19.09.15.
 */
public class HighScoreService {

    private static final String HIGHSCORE_KEY = "HIGHSCORE_KEY";

    private static final int HIGHSCORE_LIMIT = 3;
    private static final Comparator<String> SCORE_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return new CompareToBuilder().append(Double.parseDouble(s1), Double.parseDouble(s2)).build();
        }
    };
    private static final String TAG = "HighScoreService";
    private DatabaseService databaseService = new DatabaseService();

    public void updateScores(double score) {
        Log.i(TAG, ">> Updating highscores with score=" + score);
        final List<String> highscores = databaseService.get(HIGHSCORE_KEY);
        Log.i(TAG, ">> Currect highscores=" + highscores);
        highscores.add(String.valueOf(score));
        sort(highscores, SCORE_COMPARATOR);
        final Iterable<String> limitedHighscores = limit(highscores, HIGHSCORE_LIMIT);
        final List<String> newHighscores = newArrayList(limitedHighscores);
        databaseService.save(HIGHSCORE_KEY, newHighscores);
        Log.i(TAG, "<< Updating highscores finished with newHighscores=" + newHighscores);
    }

    public List<String> getHighscores() {
        Log.d(TAG, ">> Getting highscores");
        final List<String> result = databaseService.get(HIGHSCORE_KEY);
        sort(result, SCORE_COMPARATOR);
        Log.d(TAG, "<< Getting finished with result=" + result);
        return result;
    }
}
