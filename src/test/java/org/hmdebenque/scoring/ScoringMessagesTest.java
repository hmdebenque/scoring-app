package org.hmdebenque.scoring;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ScoringMessagesTest {

    @Localized("fr")
    ScoringMessages scoringMessages;

    @Test
    void simpleMessage() {
        assertEquals("Welcome", scoringMessages.welcome());
    }
}