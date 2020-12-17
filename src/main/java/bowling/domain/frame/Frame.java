package bowling.domain.frame;

public interface Frame {

    int NORMAL_MAX_BOWL_PITCH = 2;
    int FINAL_MAX_BOWL_PITCH = 3;

    Frame bowl(Point point);

    boolean isLastPitch();

    String getScores();
}
