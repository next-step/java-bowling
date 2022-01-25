package bowling.domain.frame;

import bowling.domain.KnockedPins;

public class LastDefaultFrame extends DefaultFrame {
    private KnockedPins thirdKnockedPins;

    public LastDefaultFrame() {
    }

    public LastDefaultFrame(KnockedPins firstKnockedPins) {
        this.firstKnockedPins = firstKnockedPins;
    }

    public LastDefaultFrame(KnockedPins firstKnockedPins, KnockedPins secondKnockedPins) {
        this.firstKnockedPins = firstKnockedPins;
        this.secondKnockedPins = secondKnockedPins;
    }

    public LastDefaultFrame(KnockedPins firstKnockedPins, KnockedPins secondKnockedPins, KnockedPins thirdKnockedPins) {
        this.firstKnockedPins = firstKnockedPins;
        this.secondKnockedPins = secondKnockedPins;
        this.thirdKnockedPins = thirdKnockedPins;
    }

    @Override
    public void makeScore(KnockedPins knockedPins, int index) {
        if (index == firstScoreKey) {
            this.firstKnockedPins = knockedPins;
        }
        if (index == secondScoreKey) {
            this.secondKnockedPins = knockedPins;
        }
        if (index == thirdScoreKey) {
            this.thirdKnockedPins = knockedPins;
        }
    }

    public boolean hasDoneFirstPitch() {
        return this.firstKnockedPins != null;
    }

    public boolean hasDoneSecondPitch() {
        return thirdKnockedPins == null && firstKnockedPins != null && secondKnockedPins != null;
    }

    @Override
    public String convert() {
        if (isEmpty(firstKnockedPins)) {
            return "";
        }
        if (hasDoneFirstPitch() && secondKnockedPins == null && thirdKnockedPins == null) {
            return firstKnockedPins.convert() + "|";
        }

        if (hasDoneSecondPitch() && isSpare(firstKnockedPins, secondKnockedPins) && thirdKnockedPins == null) {
            return firstKnockedPins.convert() + "|" + "/|";
        }
        if (hasDoneSecondPitch() && !isSpare(firstKnockedPins, secondKnockedPins)) {
            return firstKnockedPins.convert() + "|" + secondKnockedPins.convert();
        }
        if (hasDoneSecondPitch() && isSpare(firstKnockedPins, secondKnockedPins) && thirdKnockedPins != null) {
            return firstKnockedPins.convert() + "|" + "/|" + "|" + thirdKnockedPins.convert();
        }
        if (!secondKnockedPins.isStrike() && isSpare(secondKnockedPins, thirdKnockedPins)) {
            return firstKnockedPins.convert() + "|" + secondKnockedPins.convert() + "|/";
        }
        return firstKnockedPins.convert() + "|" + secondKnockedPins.convert() + "|" + thirdKnockedPins.convert();
    }

    @Override
    public boolean isSpare(KnockedPins knockedPinsA, KnockedPins knockedPinsB) {
        if (knockedPinsA.isStrike() || knockedPinsB.isStrike()) {
            return false;
        }
        return knockedPinsA.add(knockedPinsB).equals(new KnockedPins(10));
    }

    @Override
    public boolean isLastFrame() {
        return true;
    }
}
