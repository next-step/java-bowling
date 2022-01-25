package bowling.domain.frame;

import bowling.domain.KnockedPins;
import bowling.domain.Score;
import bowling.domain.pitch.Normal;

public class NormalDefaultFrame extends DefaultFrame {

    public NormalDefaultFrame(int frameNumber) {
        super(frameNumber);
    }

    private boolean isFirstPitch() {
        return pitchs.isEmpty();
    }

    private boolean isSecondPitch() {
        return !pitchs.isEmpty() && pitchs.get(0);
    }

    @Override
    public void bowl(KnockedPins knockedPins, int index) {
        pitchs.add(new Normal(knockedPins));
    }

    private int calculateScore(Frames frames) {
        if (score.canCalucateScore()) {
            return score.getScore();
        }
        return nextFrame(frames).cacluateAdditionalScore(score,frames);
    }

    private int cacluateAdditionalScore(Score beforeScore,Frames frames) {
        beforeScore.bowl(firstKnockedPins.getKnockedPins());
        if (beforeScore.canCalucateScore()) {
            return beforeScore.getScore();
        }
        if (isStrike()) {
            nextFrame(frames).cacluateAdditionalScore(beforeScore,frames);
        }
        return nextFrame(frames).cacluateAdditionalScore(beforeScore, frames);
    }

    private Score createScore() {
        if (isSpare(firstKnockedPins, secondKnockedPins)) {
            return new Score(10, 1);
        }
        if (isStrike()) {
            return new Score(10, 2);
        }
        return new Score(firstKnockedPins.getKnockedPins() + secondKnockedPins.getKnockedPins(), 0);
    }

    public NormalDefaultFrame nextFrame(Frames frames) {
        return (NormalDefaultFrame) frames.getFrames().get(frameNumber + 1);
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
