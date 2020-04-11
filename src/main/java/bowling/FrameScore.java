package bowling;

public interface FrameScore {

    void pitch(final int scoreCount);

    int sum();

    boolean isOver();
}
