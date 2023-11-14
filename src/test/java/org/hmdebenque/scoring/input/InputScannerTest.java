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
package org.hmdebenque.scoring.input;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hmdebenque.scoring.input.InputValue.A;
import static org.hmdebenque.scoring.input.InputValue.B;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@QuarkusTest
class InputScannerTest {

  @Test
  @Timeout(value = 1, unit = SECONDS)
  // Needed in case blocking bug occurs
  void exerciseTest() {
    try (PipedOutputStream src = new PipedOutputStream();
        PipedInputStream input = new PipedInputStream(src); ) {
      InputScanner scanner = new InputScanner(input);
      src.write("A".getBytes(StandardCharsets.UTF_8));
      assertEquals(Optional.of(A), scanner.getInput());
      src.write("B".getBytes(StandardCharsets.UTF_8));
      assertEquals(Optional.of(B), scanner.getInput());
      src.write("R".getBytes(StandardCharsets.UTF_8));
      assertEquals(Optional.empty(), scanner.getInput());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
