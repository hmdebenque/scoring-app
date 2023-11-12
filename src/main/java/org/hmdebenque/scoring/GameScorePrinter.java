package org.hmdebenque.scoring;

import io.quarkus.qute.Template;
import jakarta.inject.Inject;

import java.util.Arrays;
import java.util.List;

public class GameScorePrinter {

    private final List<ScoreValue> GAME_WON = Arrays.asList(new ScoreValue[]{ScoreValue.WON, ScoreValue.LOST});

    @Inject
    private Template template;

    private ScoringMessages scoringMessages;

    public String welcomeMessage() {
        return scoringMessages.welcome();
    }

    public String printGameScore(GameScore score) {
        ScoreValue scoreA = score.getScoreA();
        ScoreValue scoreB = score.getScoreB();

        // Case game is running normally

        // Case deuce

        // Case advantage

        // Case game won
        if (GAME_WON.contains(scoreA) && GAME_WON.contains(scoreB)) {

        }
        return "";
    }

}
