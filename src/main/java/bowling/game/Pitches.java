package bowling.game;

public interface Pitches {
    int throwBall(final int pinCount);

    boolean hasChance();

    String getPitchesStates();
}
