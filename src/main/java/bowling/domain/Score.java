package bowling.domain;

public class Score {
    private int pins;
    private int availability;

    public Score(int pins, int availability) {
        this.pins = pins;
        this.availability = availability;
    }

    public String updateScore(int score) {
        useAvailability();
        return getFormattedScore(updateNumberOfPins(score));
    }

    private void useAvailability() {
        if (isAvailable() && pins > 0) {
            --availability;
            return;
        }
        throw new IllegalStateException("점수를 더 이상 입력할 수 없습니다.");
    }

    private int updateNumberOfPins(int score) {
        if (pins < score) {
            throw new IllegalArgumentException("남아있는 볼링핀 개수보다 입력한 점수가 더 많습니다.");
        }
        pins = pins - score;
        return score;
    }

    public boolean isAvailable() {
        if (availability <= 0) {
            return false;
        }
        return true;
    }

    private String getFormattedScore(int score) {
        if (score == 0) {
            return "-";
        }
        if (availability == 1 && pins == 0) {
            return "X";
        }
        if (availability == 0 && pins == 0) {
            return "/";
        }
        return String.valueOf(score);
    }
}
