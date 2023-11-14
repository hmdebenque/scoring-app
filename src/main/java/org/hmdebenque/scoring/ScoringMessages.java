package org.hmdebenque.scoring;

import io.quarkus.qute.i18n.Message;
import io.quarkus.qute.i18n.MessageBundle;

@MessageBundle
public interface ScoringMessages {

    @Message("Welcome to the scoring app.")
    String welcome();

    @Message("Player A : {scoreA} / Player B : {scoreB}")
    String announcement(int scoreA, int scoreB);

    @Message("Game is deuce")
    String announcement_deuce();

    @Message("Advantage to player {playerWithAdvantage}")
    String announcement_advantage(String playerWithAdvantage);

    @Message("Player {winner} wins the game")
    String announcement_game_finished(String winner);
}
