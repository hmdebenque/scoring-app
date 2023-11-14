package org.hmdebenque.scoring;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class ScoringMessagesTest {

    @Localized("fr")
    ScoringMessages scoringMessages;

    @Test
    void simpleMessage() {
        assertEquals("Welcome", scoringMessages.welcome());
    }
}