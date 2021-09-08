package bowling.ui;

import bowling.domain.BowlingGame;
import bowling.domain.frame.*;
import bowling.domain.frame.rolling.FinalRollings;
import bowling.domain.frame.rolling.NormalRollings;
import bowling.domain.frame.rolling.Rollings;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {

    private static final String FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_NAME = "|  %s |";
    private static final String NONE = "      |";

    private ResultView() {
        throw new IllegalStateException();
    }

    public static void printFrame() {
        System.out.println(FRAME);
    }

    public static void printFrameByPlayer(BowlingGame bowlingGame) {
        System.out.printf(PLAYER_NAME, bowlingGame.playerName());
        List<Frame> frames = bowlingGame.frames();
        frames.stream()
                .map(frame -> displayChar(frame.rollings()))
                .forEach(System.out::print);

        printNonFrame(frames.size());

    }

    private static void printNonFrame(int sizeOfFrame) {
        IntStream.range(0, 10 - sizeOfFrame)
                .mapToObj(i -> NONE)
                .forEach(System.out::print);
    }

    private static String displayChar(Rollings rollings) {
        if (rollings instanceof NormalRollings) {
            return NormalFrameDisplay.display((NormalRollings) rollings);
        }
        return FinalFrameDisplay.display((FinalRollings) rollings);
    }

}
