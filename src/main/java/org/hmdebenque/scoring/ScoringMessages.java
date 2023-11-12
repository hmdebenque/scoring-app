package org.hmdebenque.scoring;

import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

@MessageBundle
public interface ScoringMessages {

    @Message("Welcome to the scoring app.")
    String welcome();

}
