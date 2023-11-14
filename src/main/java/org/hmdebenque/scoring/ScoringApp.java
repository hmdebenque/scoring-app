package org.hmdebenque.scoring;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.hmdebenque.scoring.input.InputScanner;
import org.hmdebenque.scoring.input.InputValue;

import java.util.Optional;

@QuarkusMain
public class ScoringApp implements QuarkusApplication {


    @Inject
    GameScorePrinter gameScorePrinter;

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
