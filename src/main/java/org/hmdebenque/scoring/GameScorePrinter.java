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

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class GameScorePrinter {

  @Inject private ScoringMessages scoringMessages;

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
