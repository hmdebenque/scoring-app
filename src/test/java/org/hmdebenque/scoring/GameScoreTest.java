/*
MIT License

Copyright (c) 2023 Henri-Mayeul de Benque

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package org.hmdebenque.scoring;

import static org.hmdebenque.scoring.ScoreValue.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class GameScoreTest {

  /**
   * input "ABABAA" yields:
   *
   * <ul>
   *   <li>“Player A : 15 / Player B : 0”
   *   <li>“Player A : 15 / Player B : 15”
   *   <li>“Player A : 30 / Player B : 15”
   *   <li>“Player A : 30 / Player B : 30”
   *   <li>“Player A : 40 / Player B : 30”
   *   <li>“Player A wins the game
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
