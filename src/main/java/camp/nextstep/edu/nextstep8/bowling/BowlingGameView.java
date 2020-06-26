package camp.nextstep.edu.nextstep8.bowling;

import java.util.Optional;

import static camp.nextstep.edu.nextstep8.bowling.constant.BowlingRule.MAX_FRAME;

public class BowlingGameView {
    private static final String EMPTY = "";
    private static final String BASE_FORMAT = "|%5s\t";
    private static final String PIPE = "|";
    private static final String HEADER_NAME = "NAME";

    private static int getLoopTimes(int frameCount) {
        return frameCount > MAX_FRAME ? frameCount : MAX_FRAME;
    }

    public static void showDashboard(String player, ScoreBoard scoreBoard) {
        int loopTimes = getLoopTimes(scoreBoard.getFrameCount());
        makeResult(index -> String.format("%02d", index), HEADER_NAME, loopTimes);
        makeResult(index ->
                Optional.ofNullable(scoreBoard.getFrame(index))
                .map(Frame::getFrameResultSymbol)
                .orElse(EMPTY), player, loopTimes);
    }

    private static void makeResult(TextRenderer renderer, String title, int loopTimes) {
        StringBuilder output = new StringBuilder();
        output.append(wrapFormat(title));
        for(int i = 1; i <= loopTimes; i++) {
            output.append(wrapFormat(renderer.render(i)));
        }
        output.append(PIPE);
        System.out.println(output.toString());
    }

    private static String wrapFormat(String value) {
        return String.format(BASE_FORMAT, value);
    }
}
