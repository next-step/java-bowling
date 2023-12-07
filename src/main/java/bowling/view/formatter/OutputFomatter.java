package bowling.view.formatter;

import bowling.domain.Ball;
import bowling.domain.Frame;
import bowling.domain.FrameBoard;
import java.util.List;
import java.util.stream.Collectors;

public class OutputFomatter {
    public String toName(FrameBoard frameBoard) {
        return frameBoard.getPlayerName().getPlayerName();
    }

    public String toBoard(FrameBoard frameBoard) {
        List<String> frames = frameBoard.getFrames().stream()
                .map(this::toFrame)
                .collect(Collectors.toList());
        String board = String.join("  | ", frames);
        if (frameBoard.needLastFrameBonus()) {
            String bonusBall = getBallMark(frameBoard.getBonusBall());
            return  board + "|" + bonusBall;
        }
        return board + "  ";
    }

    public String toFrame(Frame rawFrame) {
        Ball firstBall = rawFrame.getFirstBall();

        String firstBallMark = getBallMark(firstBall);
        String secondBallMark = getBallMark(rawFrame.getSecondBall());

        if (firstBall.isStrike()) {
            return firstBallMark + "  ";
        }
        if (rawFrame.isEmpty()) {
            return firstBallMark + " " + secondBallMark;
        }
        return firstBallMark + "|" + secondBallMark;
    }

    private String getBallMark(Ball firstBall) {
        if (firstBall.isOpen()) {
            return String.valueOf(firstBall.getBall());
        }
        if (!firstBall.isOpen()) {
            return firstBall.getBallStatus().getMark();
        }
        return "";
    }
}
