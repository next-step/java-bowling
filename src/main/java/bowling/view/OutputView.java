package bowling.view;

import bowling.domain.Name;
import bowling.domain.Result;
import bowling.domain.frame.Frame;

import java.util.List;

public class OutputView {

    private static final String MARK = "|";

    private static final int FINAL_ROUND = 10;
    private static final String EMPTY_MARK = "       ";
    private final String header = getHeader();

    private String getHeader() {
        StringBuilder sb = new StringBuilder();

        sb.append(MARK);
        sb.append(String.format(" NAME "));

        for (int i = 1; i <= 10; i++) {
            sb.append(MARK);
            sb.append(String.format("  %s   ", lpadZero(i)));
        }
        sb.append(MARK);
        return sb.toString();
    }

    public void print(Name name, List<Result> results) {
        System.out.println(header);
        System.out.println(getDesc(name, results));
        System.out.println(getScore(results));
    }

    private String getScore(List<Result> results) {
        int total = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(MARK);
        sb.append("      ");

        for (Result result : results) {
            sb.append(MARK);

            int score = result.getScore();
            if (score == -1) {
                sb.append(EMPTY_MARK);
                continue;
            }
            total += score;

            sb.append(rpad(total+""));
        }

        addEmptyMark(results, sb);
        return sb.toString();
    }

    private String getDesc(Name name, List<Result> results) {
        StringBuilder sb = new StringBuilder();
        sb.append(MARK);
        sb.append(String.format("  %s ", name));

        for (Result result : results) {
            sb.append(MARK);
            sb.append(rpad(result.getDesc()));
        }

        addEmptyMark(results, sb);
        return sb.toString();
    }

    private void addEmptyMark(List<Result> results, StringBuilder sb) {
        int cntEmpty = 10 - results.size();

        for (int i = 0; i < cntEmpty; i++) {
            sb.append(MARK);
            sb.append(EMPTY_MARK);
        }
        sb.append(MARK);
    }

    private int getScore(int index, List<Result> results) {
        if (isMaxOver(results.size(), index)) {
            return Frame.IN_VALID_SCORE;
        }

        int score = results.get(index).getScore();
        if (score == Frame.IN_VALID_SCORE) {
            return Frame.IN_VALID_SCORE;
        }

        return score;
    }

    private static String lpadZero(int round) {
        if (round == FINAL_ROUND) {
            return round + "";
        }

        return "0" + round;
    }

    private boolean isMaxOver(int size, int index) {
        return index > (size - 1);
    }

    private String rpad(String scoreMark) {
        StringBuilder sb = new StringBuilder();

        int cnt = 5 - scoreMark.length();
        for (int i = 0; i < cnt; i++) {
            sb.append(" ");
        }
        return "  "+ scoreMark + sb;
    }
}
