package org.hmdebenque.scoring;

import static org.hmdebenque.scoring.ScoreValue.*;

public class GameScore {

    public static final String PLAYER_A = "A";
    public static final String PLAYER_B = "B";
    private int scoreA = 0;
    private int scoreB = 0;

    public void scoreA() {
        scoreA += 1;
    }

    public void scoreB() {
        scoreB += 1;
    }

    public ScoreValue getScoreA() {
        return getScoreFromPoints(scoreA, scoreB);
    }

    public ScoreValue getScoreB() {
        return getScoreFromPoints(scoreB, scoreA);
    }

    private ScoreValue getScoreFromPoints(int pointsOfScoring, int pointsOfAdversary) {
        return switch (pointsOfScoring) {
            case 0 -> pointsOfAdversary > 3 ? LOST : LOVE;
            case 1 -> pointsOfAdversary > 3 ? LOST : FIFTEEN;
            case 2 -> pointsOfAdversary > 3 ? LOST : THIRTY;
            case 3 -> {
                if (pointsOfAdversary > 4) {
                    yield LOST;
                } else if (pointsOfAdversary == 4) {
                    yield DISADVANTAGE;
                } else if (pointsOfAdversary == 3) {
                    yield DEUCE;
                } else {
                    yield FORTY;
                }
            }
            default -> {
                if (pointsOfAdversary < pointsOfScoring - 1) {
                    yield WON;
                } else if (pointsOfAdversary == pointsOfScoring - 1) {
                    yield ADVANTAGE;
                } else if (pointsOfAdversary == pointsOfScoring) {
                    yield DEUCE;
                } else if (pointsOfAdversary == pointsOfScoring + 1) {
                    yield DISADVANTAGE;
                } else {
                    yield LOST;
                }
            }
        };
    }

    public boolean isFinished() {
        return FINISHED_SCORES.contains(getScoreA()) && FINISHED_SCORES.contains(getScoreB());
    }

    public String getWinner() {
        if (isFinished()) {
            return getScoreA() == WON ? PLAYER_A : PLAYER_B;
        }
        return null;
    }

    public boolean isDeuce() {
        return scoreA == scoreB;
    }

    /**
     * True if players still have a counting score aka: love, 15, 30, 40.
     */
    public boolean isCounting() {
        return COUNTING_SCORES.contains(getScoreA()) && COUNTING_SCORES.contains(getScoreB());
    }

    public boolean isAdvantage() {
        return Math.abs(scoreA - scoreB) == 1 && Math.min(scoreA, scoreB) > 2;
    }

    public String getAdvantaged() {
        if (isAdvantage()) {
            return getScoreA() == ADVANTAGE ? PLAYER_A : PLAYER_B;
        }
        return null;
    }
}
