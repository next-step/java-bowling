package bowling.domain;

public class FinalFrame {
    private final Score score;
    private int availability;

    public FinalFrame() {
        score = new Score();
        availability = 3;
    }

    public String inputScore(int inputScore) {
        if (isAvailable()) {
            --availability;
            String formattedScore = getFormattedScore(score.updateScore(inputScore));
            applyBenefitOption(inputScore);
            return formattedScore;
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

    private void applyBenefitOption(int lastScore) {
        if (lastScore == 10) {
            score.fillPins();
            return;
        }
        if (score.isPinCleared()) {
            score.fillPins();
            return;
        }
        if (availability == 1 && !score.isPinCleared()) {
            availability = 0;
        }
    }
}
