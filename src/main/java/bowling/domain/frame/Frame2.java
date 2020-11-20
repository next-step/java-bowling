package bowling.domain.frame;

import bowling.domain.point.Point;

public interface Frame2 {
    int MAX_FINAL_FRAME_CAN_ROLL = 3;
    int MAX_NORMAL_FRAME_CAN_ROLL = 2;

    Frame2 roll(Point point);
    boolean isFinalFrame();
    boolean isLastRoll();
    String getScores();
    int getPoint();
}
