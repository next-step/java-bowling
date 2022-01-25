package bowling.domain.frame;

import bowling.domain.KnockedPins;

public class NormalDefaultFrame extends DefaultFrame {

    public NormalDefaultFrame() {
    }

    public NormalDefaultFrame(KnockedPins firstKnockedPins) {
        this.firstKnockedPins = firstKnockedPins;
    }

    public NormalDefaultFrame(KnockedPins firstKnockedPins, KnockedPins secondKnockedPins) {
        this.firstKnockedPins = firstKnockedPins;
        this.secondKnockedPins = secondKnockedPins;
    }

    @Override
    public void makeScore(KnockedPins knockedPins, int index) {
        if (index == firstScoreKey) {
            this.firstKnockedPins = knockedPins;
        }
        if (index == secondScoreKey) {
            this.secondKnockedPins = knockedPins;
        }
    }

    public boolean isStrike() {
        return firstKnockedPins.equals(new KnockedPins(10));
    }

    @Override
    public boolean isSpare(KnockedPins knockedPinsA, KnockedPins knockedPinsB) {
        return knockedPinsA.add(knockedPinsB).equals(new KnockedPins(10));
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    public boolean hasDoneFirstPitch() {
        return firstKnockedPins != null && secondKnockedPins == null;
    }

    @Override
    public boolean hasDoneSecondPitch() {
        return false;
    }

    public String convert() {
        if (isEmpty(firstKnockedPins)) {
            return "";
        }
        if (isStrike()) {
            return "X";
        }
        if (hasDoneFirstPitch() && !isStrike()) {
            return firstKnockedPins.convert() + "|";
        }
        if (isSpare(firstKnockedPins, secondKnockedPins)) {
            return firstKnockedPins.convert() + "|" + "/";
        }
        return firstKnockedPins.convert() + "|" + secondKnockedPins.convert();
    }
}
