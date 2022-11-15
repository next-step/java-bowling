package bowling.view;

import bowling.domain.*;

public class OutputView {

    private static final String MARK = "|";
    private static final int FINAL_ROUND = 10;

    private final StringBuilder sb;

    public OutputView(StringBuilder result) {
        this.sb = result;
    }

    public static OutputView init() {
        StringBuilder result = createFirstLine();
        return new OutputView(result);
    }

    private static StringBuilder createFirstLine() {
        StringBuilder sb = new StringBuilder();

        sb.append(MARK);
        sb.append(String.format(" NAME "));

        for (int i = 1; i <= 10; i++) {
            sb.append(MARK);
            sb.append(String.format(" %s ", lpadZero(i)));
        }
        sb.append(MARK);

        sb.append(System.lineSeparator());
        return sb;
    }

    private static String lpadZero(int round) {
        if (round == FINAL_ROUND) {
            return round + "";
        }

        return "0" + round;
    }

    public void print(Name name, Frames frames) {
        appendMark();
        sb.append(String.format("  %s ", name));

        for (int i = 0; i < 10; i++) {
            appendMark();
            appendBody(frames, i);
        }

        System.out.println(sb);
    }

    private String appendBody(Frames frames, int index) {
        if (index > frames.size() - 1) {
            return "";
        }

        if (index == 10) {
            return getFinalFrameScore(frames.get(index));
        }

        return getNormalFrameScore(frames.get(index), index);
    }

    private String getNormalFrameScore(Frame2 frame, int index) {
        StringBuilder sb = new StringBuilder();

        Result first = frame.getResult(index);
        sb.append(getScoreMark(first, frame.getCount(index)));

        if (frame.getRound() == 2 && Result.STRIKE != first) {
            appendMark();
            sb.append(getScoreMark(first, frame.getCount(1)));
        }
        return sb.toString();
    }

    private String getFinalFrameScore(Frame2 frame) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < frame.getRound(); i++) {
            if (1 != 0) {
                sb.append(MARK);
            }
            sb.append(getScoreMark(frame.getResult(i), frame.getCount(i)));
        }
        return sb.toString();
    }

    private void appendMark() {
        sb.append(MARK);
    }

    private String getScoreMark(Result result, int count) {
        if (Result.STRIKE == result) {
            return "X";
        }

        if (Result.SPARE == result) {
            return "/";
        }

        if (Result.GUTTER == result) {
            return "-";
        }

        return count + "";
    }
}
