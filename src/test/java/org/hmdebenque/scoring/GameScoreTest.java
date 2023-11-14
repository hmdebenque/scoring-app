package org.hmdebenque.scoring;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.hmdebenque.scoring.ScoreValue.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class GameScoreTest {

    /**
     * input "ABABAA" yields:
     * <ul>
     * <li>“Player A : 15 / Player B : 0”</li>
     * <li>“Player A : 15 / Player B : 15”</li>
     * <li>“Player A : 30 / Player B : 15”</li>
     * <li>“Player A : 30 / Player B : 30”</li>
     * <li>“Player A : 40 / Player B : 30”</li>
     * <li>“Player A wins the game</li>
     * </ul>
     */
    @Test
    void exerciseTestCase() {
        var score = new GameScore();
        assertScores(score, LOVE, LOVE);
        score.scoreA();
        assertScores(score, FIFTEEN, LOVE);
        score.scoreB();
        assertScores(score, FIFTEEN, FIFTEEN);
        score.scoreA();
        assertScores(score, THIRTY, FIFTEEN);
        score.scoreB();
        assertScores(score, THIRTY, THIRTY);
        score.scoreA();
        assertScores(score, FORTY, THIRTY);
        score.scoreA();
        assertScores(score, WON, LOST);
    }

    @Test
    void advantageAndDeuce() {
        var score = new GameScore();
        assertScores(score, LOVE, LOVE);
        score.scoreA();
        assertScores(score, FIFTEEN, LOVE);
        score.scoreA();
        assertScores(score, THIRTY, LOVE);
        score.scoreA();
        assertScores(score, FORTY, LOVE);
        score.scoreB();
        assertScores(score, FORTY, FIFTEEN);
        score.scoreB();
        assertScores(score, FORTY, THIRTY);
        score.scoreB();
        assertScores(score, DEUCE, DEUCE);
        score.scoreB();
        assertScores(score, DISADVANTAGE, ADVANTAGE);
        score.scoreA();
        assertScores(score, DEUCE, DEUCE);
        score.scoreA();
        assertScores(score, ADVANTAGE, DISADVANTAGE);
        score.scoreA();
        assertScores(score, WON, LOST);
    }

    @Test
    void straitToTheTop() {
        var score = new GameScore();
        assertScores(score, LOVE, LOVE);
        score.scoreA();
        assertScores(score, FIFTEEN, LOVE);
        score.scoreA();
        assertScores(score, THIRTY, LOVE);
        score.scoreA();
        assertScores(score, FORTY, LOVE);
        score.scoreA();
        assertScores(score, WON, LOST);
    }

    @Test
    void goForCoverage() {
        var score = new GameScore();
        assertScores(score, LOVE, LOVE);
        score.scoreA();
        assertScores(score, FIFTEEN, LOVE);
        score.scoreA();
        assertScores(score, THIRTY, LOVE);
        score.scoreA();
        assertScores(score, FORTY, LOVE);
        score.scoreB();
        assertScores(score, FORTY, FIFTEEN);
        score.scoreB();
        assertScores(score, FORTY, THIRTY);
        score.scoreB();
        assertScores(score, DEUCE, DEUCE);
        score.scoreB();
        assertScores(score, DISADVANTAGE, ADVANTAGE);
        score.scoreB();
        assertScores(score, LOST, WON);
    }

    private static void assertScores(GameScore score, ScoreValue scoreA, ScoreValue scoreB) {
        assertEquals(scoreA, score.getScoreA());
        assertEquals(scoreB, score.getScoreB());
    }
}