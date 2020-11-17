package bowling.domain.frame;

import bowling.domain.point.Point;

public interface Frame2 {
    int MAX_FINAL_FRAME_CAN_ROLL = 3;
    int MAX_NORMAL_FRAME_CAN_ROLL = 2;
    int FIRST_ROLL = 1;
    int SECOND_ROLL = 2;
    int BONUS_ROLL = 3;

    Frame2 roll(Point point);
    boolean isFinalFrame();
    boolean isLastRoll();
    String getScores();
    String getPoint();
}
