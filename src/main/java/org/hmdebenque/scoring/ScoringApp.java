package org.hmdebenque.scoring;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.hmdebenque.scoring.input.InputScanner;
import org.hmdebenque.scoring.input.InputValue;

import java.util.Optional;

@QuarkusMain
public class ScoringApp implements QuarkusApplication {
    @Override
    public int run(String... args) throws Exception {
        GameScore gameScore = new GameScore();


        try (InputScanner inputScanner = new InputScanner(System.in)) {
//            do {
//                Optional<InputValue> input = inputScanner.getInput();
//                if (input.isPresent()) {
//                    switch (input.get()) {
//                        case A -> gameScore.scoreA();
//                        case B -> gameScore.scoreB();
//                    }
//
//                }
//            }
        }


        return 0;
    }
}
