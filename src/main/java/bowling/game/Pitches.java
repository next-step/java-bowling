package bowling.game;

import java.util.List;

public interface Pitches {
    void throwBall(final int pinCount);

    boolean hasChance();

    String getPitchesStates();

    boolean isStrikePitches();

    boolean isSparePitches();

    int getBasicScore();

    List<Integer> getPitchesPinCounts();
}
