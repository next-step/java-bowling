package bowling.domain;

public class Player {
    private final Name name;
    private final Frames frames;
    private final Scores scores;

    public Player(String name) {
        this.name = new Name(name);
        this.frames = new Frames();
        this.scores = new Scores();
    }

    public boolean process() {
        if (frames.isEnd()) {
            frames.add();
            return false;
        }
        return true;
    }

    public void knockedDownPins(Pin pin) {
        frames.addRoll(pin);
        scores.addBonusScore(pin);
        calculateScoreIfEnd();
    }

    private void calculateScoreIfEnd() {
        if (frames.isEnd()) {
            scores.add(frames.calculateScore());
        }
    }

    public Name getName() {
        return name;
    }

    public Frames getFrames() {
        return frames;
    }

    public Scores getScores() {
        return scores;
    }
}
