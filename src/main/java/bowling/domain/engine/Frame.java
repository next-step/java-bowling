package bowling.domain.engine;

public interface Frame {

    void bowl(final int pins);

    int round();

    int score();

    boolean isNextFrame();

    State state();

    boolean isEnd();

    String printFrame();
}
