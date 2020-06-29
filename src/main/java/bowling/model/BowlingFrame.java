package bowling.model;

public interface BowlingFrame {

    void bowl(int scoreCount);

    boolean isOver();

    int sum();

    FrameScore getFrameScore();
}
