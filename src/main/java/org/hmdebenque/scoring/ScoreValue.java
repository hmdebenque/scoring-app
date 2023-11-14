package org.hmdebenque.scoring;

import java.util.List;

public enum ScoreValue {
    LOVE,
    FIFTEEN,
    THIRTY,
    FORTY,
    DEUCE,
    ADVANTAGE,
    DISADVANTAGE,
    WON,
    LOST;
    public static final List<ScoreValue> FINISHED_SCORES = List.of(WON, LOST);
    public static final List<ScoreValue> COUNTING_SCORES = List.of(LOVE, FIFTEEN, THIRTY, FORTY);
}
