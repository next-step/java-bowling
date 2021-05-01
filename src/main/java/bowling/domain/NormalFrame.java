package bowling.domain;

public class NormalFrame {
    private final Score score;
    private int availability;

    public NormalFrame() {
        score = new Score();
        availability = 2;
    }

    public String inputScore(int input) {
        if (isAvailable()) {
            --availability;
            return getFormattedScore(score.updateScore(input));
        }
        throw new IllegalStateException("점수를 더 이상 입력할 수 없습니다.");
    }

    public boolean isAvailable() {
        if (availability > 0) {
            return true;
        }
        return false;
    }

    private String getFormattedScore(int lastScore) {
        if (lastScore == 0) {
            return "-";
        }
        if (lastScore == 10) {
            return "X";
        }
        if (score.isPinCleared()) {
            return "/";
        }
        return String.valueOf(lastScore);
    }
}
