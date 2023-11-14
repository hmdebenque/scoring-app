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

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import java.util.Optional;
import org.hmdebenque.scoring.input.InputScanner;
import org.hmdebenque.scoring.input.InputValue;

@QuarkusMain
public class ScoringApp implements QuarkusApplication {

  @Inject GameScorePrinter gameScorePrinter;

  @Override
  public int run(String... args) {
    System.out.println(gameScorePrinter.welcomeMessage());
    GameScore gameScore = new GameScore();
    try (InputScanner inputScanner = new InputScanner(System.in)) {
      do {
        Optional<InputValue> input = inputScanner.getInput();
        if (input.isPresent()) {
          switch (input.get()) {
            case A -> gameScore.scoreA();
            case B -> gameScore.scoreB();
          }
          System.out.println(gameScorePrinter.printGameScore(gameScore));
        }
      } while (!gameScore.isFinished());
    }
    return 0;
  }
}
