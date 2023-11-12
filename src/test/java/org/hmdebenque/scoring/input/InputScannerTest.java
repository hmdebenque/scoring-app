package org.hmdebenque.scoring.input;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class InputScannerTest {

    @Test
    @Timeout(value = 1, unit = SECONDS) // Needed in case blocking bug occurs
    void exerciseTest() {
        try (PipedOutputStream src = new PipedOutputStream();
             PipedInputStream input = new PipedInputStream(src);) {
            InputScanner scanner = new InputScanner(input);
            src.write("A".getBytes(StandardCharsets.UTF_8));
            assertEquals(InputValue.A, scanner.getInput());
            src.write("B".getBytes(StandardCharsets.UTF_8));
            assertEquals(InputValue.B, scanner.getInput());
            src.write("R".getBytes(StandardCharsets.UTF_8));
            assertNull(scanner.getInput());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}