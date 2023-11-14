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

import static org.hmdebenque.scoring.input.InputValue.A;
import static org.hmdebenque.scoring.input.InputValue.B;

import io.quarkus.logging.Log;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

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
