package bowling.domain.frame;

public interface Frame {


    int FRAME_FIRST_PITCH_END = 1;
    int FRAME_SECOND_PITCH_END = 2;
    int FRAME_THIRD_PITCH_END = 3;

    int NORMAL_MAX_BOWL_PITCH = 2;
    int FINAL_MAX_BOWL_PITCH = 3;

    Frame bowl(Point point);

    boolean isLastPitch();

    String getScores();
}
