package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Name;
import bowling.domain.Result;

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
        System.out.println(getBody(name, results));
//        System.out.println(getScore(bowling));
    }

    private String getBody(Name name, List<Result> results) {
        StringBuilder sb = new StringBuilder();
        sb.append(MARK);
        sb.append(String.format("  %s ", name));

        for (int i = 0; i < 10; i++) {
            sb.append(MARK);
            sb.append(formatScore(i, results));
        }
        sb.append(MARK);
        return sb.toString();
    }

    private String formatScore(int index, List<Result> results) {
        if (isMaxOver(results, index)) {
            return EMPTY_MARK;
        }

        String desc = results.get(index).getDesc();
        return rpad(desc);
    }

//    private String getScore(Bowling bowling) {
//        return getMarkdedString(EMPTY_MARK, i -> getFrameScore(bowling, i));
//    }
//
//    private String getFrameScore(Bowling bowling, Integer index) {
//        if (isMaxOver(bowling, index) || bowling.isEmpty(index) || bowling.getScore(index).isEmpty()) {
//            return EMPTY_MARK;
//        }
//
//        return bowling.getScore(index)
//                .orElseThrow(() -> new IllegalArgumentException("점수를 조회할 수 없습니다"))
//                .toString();
//    }

    private static String lpadZero(int round) {
        if (round == FINAL_ROUND) {
            return round + "";
        }

        return "0" + round;
    }

    private boolean isMaxOver(List<Result> results, int index) {
        return index > results.size() - 1;
    }

    private String formatScore(String scoreMark) {
        return "  " + rpad(scoreMark);
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
