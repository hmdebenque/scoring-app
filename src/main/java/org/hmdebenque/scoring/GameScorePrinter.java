package org.hmdebenque.scoring;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class GameScorePrinter {

    @Inject
    private ScoringMessages scoringMessages;

    public String welcomeMessage() {
        return scoringMessages.welcome();
    }

    public String printGameScore(GameScore score) {
        ScoreValue scoreA = score.getScoreA();
        ScoreValue scoreB = score.getScoreB();

        // Case game is running normally
        if (score.isCounting()) {
            return scoringMessages.announcement(mapCountingScore(scoreA), mapCountingScore(scoreB));
        }

        // Case deuce
        if (score.isDeuce()) {
            return scoringMessages.announcement_deuce();
        }

        // Case advantage
        if (score.isAdvantage()) {
            return scoringMessages.announcement_advantage(score.getAdvantaged());
        }

        // Case game won
        if (score.isFinished()) {
            return scoringMessages.announcement_game_finished(score.getWinner());
        }
        return "";
    }

    private int mapCountingScore(ScoreValue scoreValue) {
        return switch (scoreValue) {
            case LOVE -> 0;
            case FIFTEEN -> 15;
            case THIRTY -> 30;
            case FORTY -> 40;
            default -> throw new IllegalArgumentException();
        };
    }

}
