package bowling.view;

import bowling.domain.*;

import java.util.ArrayList;
import java.util.List;

public class OutputView {

    private static final String MARK = "|";
    private static final int FINAL_ROUND = 10;
    public static final String EMPTY_MARK = "      ";

    private final StringBuilder sb;

    public OutputView(StringBuilder result) {
        this.sb = result;
    }

    public static OutputView init() {
        return new OutputView(createFirstLine());
    }

    private static StringBuilder createFirstLine() {
        StringBuilder sb = new StringBuilder();

        sb.append(MARK);
        sb.append(String.format(" NAME "));

        for (int i = 1; i <= 10; i++) {
            sb.append(MARK);
            sb.append(String.format(" %s   ", lpadZero(i)));
        }
        sb.append(MARK);

        sb.append(System.lineSeparator());
        return sb;
    }

    public void print(Name name, Frames frames) {
        appendMark();
        sb.append(String.format("  %s ", name));

        for (int i = 0; i < 10; i++) {
            appendMark();
            sb.append(getFrameScore(frames, i));
        }
        appendMark();

        System.out.println(sb);
    }

    private static String lpadZero(int round) {
        if (round == FINAL_ROUND) {
            return round + "";
        }

        return "0" + round;
    }

    private String getFrameScore(Frames frames, int index) {
        if (index > frames.size() - 1) {
            return EMPTY_MARK;
        }

        Frame frame = frames.get(index);
        if (frame.getRound() == 0) {
            return EMPTY_MARK;
        }

        return formatScore(getNormalFrameScore(frame));
    }

    private String formatScore(String scoreMark) {
        return " " + rpad(scoreMark, MARK);
    }

    private String rpad(String scoreMark, String mark) {
        StringBuilder sb = new StringBuilder();

        int cnt = 5 - scoreMark.length();
        for (int i = 0; i < cnt; i++) {
            sb.append(" ");
        }
        return scoreMark + sb;
    }

    private String getNormalFrameScore(Frame frame) {
        List<String> list = new ArrayList<>();
        for (Bowling bowling : frame) {
            list.add(getScoreMark(bowling));
        }

        return String.join(MARK, list);
    }

    private void appendMark() {
        sb.append(MARK);
    }

    private String getScoreMark(Bowling bowling) {
        Result result = bowling.getResult();
        int count = bowling.getCount();

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
