package bowling.domain;

public class Frame {
    private static final int MAXIMUM_SCORE = 10;

    String firstDelivery;
    String secondDelivery;

    Frame() {
    }

    public void delivery(int inputScore) {
        if (firstDelivery == null || firstDelivery.isBlank()) {
            firstDelivery = Score.value(inputScore, false);
            return;
        }
        secondDelivery = Score.value(inputScore, spare(inputScore));
    }

    boolean spare(int inputScore) {
        if (Score.strike(firstDelivery)) {
            return false;
        }

        int totalScore = Integer.parseInt(firstDelivery) + inputScore;
        if (totalScore > MAXIMUM_SCORE) {
            throw new IllegalArgumentException("각 프레임의 점수의 합 10을 넘을 수 없습니다.");
        }
        return totalScore == MAXIMUM_SCORE;
    }

    public boolean additionallyDeliverable() {
        return !Score.strike(firstDelivery) && secondDelivery == null;
    }

    public String getFirstDelivery() {
        return firstDelivery;
    }

    public String getSecondDelivery() {
        return secondDelivery;
    }
}
