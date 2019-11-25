package game.bowling.view;

import game.bowling.domain.ScoreBoard;

import java.io.PrintStream;
import java.util.List;
import java.util.Objects;

/**
 * Created by yusik on 2019/11/23.
 */
public class ResultView {

    private static final int FRAME_WIDTH = 6;
    private static final String FIRST_HEADER_FRAME = "NAME";
    private static final String FRAME_DELIMITER = "|";
    private static final String NEW_LINE = "\n";
    private static final String EMPTY_STATUS = "";

    private final PrintStream out;

    public ResultView(PrintStream out) {
        this.out = out;
    }

    public void render(ScoreBoard scoreBoard) {
        renderHeaderLine(scoreBoard.getFrameNos());
        renderStatusLine(scoreBoard);
        out.println(NEW_LINE);
    }

    private void renderHeaderLine(List<Integer> frameNos) {
        renderFrame(FIRST_HEADER_FRAME);
        for (Integer frameNo : frameNos) {
            renderFrame(String.format("%02d", frameNo));
        }
        out.println(FRAME_DELIMITER);
    }

    private void renderStatusLine(ScoreBoard scoreBoard) {
        renderFrame(scoreBoard.getPlayerName());
        for (String status : scoreBoard.getStatusLine()) {
            renderFrame(status);
        }
        out.println(FRAME_DELIMITER);
    }

    private void renderFrame(String frameContent) {
        out.print(FRAME_DELIMITER);
        out.print(alignCenter(frameContent));
    }

    private String alignCenter(String content) {
        if (Objects.isNull(content)) {
            content = EMPTY_STATUS;
        }
        int length = content.length();
        int leftPaddingLength = (FRAME_WIDTH - length) / 2;
        int rightPaddingLength = FRAME_WIDTH - leftPaddingLength - length;
        return String.join("", createPadding(leftPaddingLength), content, createPadding(rightPaddingLength));
    }

    private String createPadding(int length) {
        if (length <= 0) {
            return "";
        }
        return String.format("%" + length + "s", "");
    }

}
