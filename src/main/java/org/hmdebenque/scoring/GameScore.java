package org.hmdebenque.scoring;

import static org.hmdebenque.scoring.ScoreValue.*;
import static org.hmdebenque.scoring.ScoreValue.LOST;

public class GameScore {

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
}
