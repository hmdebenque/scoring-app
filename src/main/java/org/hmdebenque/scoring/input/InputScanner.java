package org.hmdebenque.scoring.input;

import io.quarkus.logging.Log;

import java.io.Closeable;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.hmdebenque.scoring.input.InputValue.A;
import static org.hmdebenque.scoring.input.InputValue.B;


public class InputScanner implements Closeable {

    public static final Pattern ANY_CHAR_PATTERN = Pattern.compile(".");
    Scanner scanner;

    public InputScanner(InputStream input) {
        scanner = new Scanner(input);
    }

    public Optional<InputValue> getInput() {
        if (scanner.hasNext()) {
            String next = scanner.findWithinHorizon(ANY_CHAR_PATTERN, 1);
            if ("A".equals(next)) {
                return Optional.of(A);
            } else if ("B".equals(next)) {
                return Optional.of(B);
            } else {
                Log.warn("Invalid input: " + next);
            }
        }
        return Optional.empty();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
