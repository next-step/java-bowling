package bowling.domain;

public class FinalFrame extends Frame {
    public FinalFrame() {
        super();
        availability = 3;
    }

    public FinalFrame(Score score) {
        super(score);
        availability = 3;
    }

    @Override
    public String addScore(int inputScore) {
        if (isAvailable()) {
            --availability;
            String formattedScore = getFormattedScore(score.updateScore(inputScore));
            applyBenefitOption(inputScore);
            return formattedScore;
        }
        throw new IllegalStateException("점수를 더 이상 입력할 수 없습니다.");
    }

    @Override
    public Frame createFrame(int frameNumber) {
        throw new IllegalStateException("마지막 프레임에서는 프레임을 생성할 수 없습니다.");
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
