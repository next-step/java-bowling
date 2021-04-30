package bowling.domain;

public class NormalFrame {
    private int pins;
    private int available;

    public NormalFrame() {
        pins = 10;
        available = 2;
    }

    public String inputScore(String score) {
        if (available <= 0) {
            throw new IllegalStateException("점수를 더 이상 입력할 수 없습니다.");
        }
        available = available - 1;
        return scoreFormat(Integer.parseInt(score));
    }

    private String scoreFormat(int score) {
        if (score == 0) {
            return "-";
        }
        pins = pins - score;
        if (pins < 0) {
            throw new IllegalArgumentException("남아있는 볼링핀 개수보다 입력한 점수가 더 많습니다.");
        }
        if (available == 1 && pins == 0) {
            available = 0;
            return "X";
        }
        if (available == 0 && pins == 0) {
            return "/";
        }
        return String.valueOf(score);
    }
}
