package org.hmdebenque.scoring;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;

@QuarkusTest
class ScoringAppTest {

    @Inject
    ScoringApp scoringApp;

    @Test
    void nominalCase() throws Exception {
        String scenario = "ABABAA";
        executeScenario(scenario);
    }

    @Test
    void deuceCase() throws Exception {
        String scenario = "AAABBBBAAA";
        executeScenario(scenario);
    }

    private void executeScenario(String scenario) throws IOException, InterruptedException {
        PipedOutputStream src = new PipedOutputStream();
        System.setIn(new PipedInputStream(src));
        Thread gameThread = new Thread(scoringApp::run);
        gameThread.start();
        src.write(scenario.getBytes(StandardCharsets.UTF_8));
        gameThread.join();
    }
}