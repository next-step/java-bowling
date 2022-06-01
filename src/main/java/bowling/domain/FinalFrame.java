package bowling.domain;

public class FinalFrame extends Frame {
    private String bonusDelivery;

    FinalFrame() {
    }

    @Override
    public void delivery(int inputScore) {
        if (super.firstDelivery == null || super.firstDelivery.isBlank()) {
            super.firstDelivery = Score.value(inputScore, false);
            return;
        }

        if (super.secondDelivery == null || super.secondDelivery.isBlank()) {
            super.secondDelivery = Score.value(inputScore, super.spare(inputScore));
            return;
        }

        bonusDelivery = Score.value(inputScore, spare(inputScore));
    }

    @Override
    boolean spare(int inputScore) {
        if (Score.strike(super.secondDelivery) || Score.spare(super.secondDelivery)) {
            return false;
        }

        return Integer.parseInt(super.secondDelivery) + inputScore == 10;
    }

    @Override
    public boolean additionallyDeliverable() {
        return bonusDelivery == null
                && (Score.strike(super.firstDelivery) || Score.spare(super.secondDelivery) || super.secondDelivery == null);
    }

    public String getBonusDelivery() {
        return bonusDelivery;
    }
}
