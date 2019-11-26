package game.bowling.view;

import game.bowling.domain.FinalScore;
import game.bowling.domain.Score;
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
        List<Score> scores = scoreBoard.getScores();
        int length = scores.size();
        renderFrame(scoreBoard.getPlayerName());
        for (int i = 0; i < length; i++) {
            renderFrame(convertStatus(scores.get(i)));
        }
        out.println(FRAME_DELIMITER);
    }

    private String convertStatus(Score score) {
        if (score.isEmpty()) {
            return EMPTY_STATUS;
        }

        if (score.isThrowing()) {
            return getSymbol(score.getFirstScore());
        }

        if (score.isFinal()) {
            String finalSymbol = getFinalSymbol(score);
            return String.join("|", FrameResult.getFormat(score), finalSymbol);
        }

        return FrameResult.getFormat(score);
    }

    private String getSymbol(int score) {
        if (score == 0) {
            return "-";
        }
        return score != 10 ? String.valueOf(score) : "X";
    }

    private String getFinalSymbol(Score score) {
        FinalScore finalScore = (FinalScore) score;
        return getSymbol(finalScore.getThirdScore());
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
