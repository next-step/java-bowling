package bowling.domain;

import java.util.Objects;

public class NormalFrame  {
    private Scores scores;

    public NormalFrame() {
        this.scores = new Scores();
    }

    public NormalFrame(Frame frame) {
        add(frame.getScores());
    }

    public boolean isNextFrame() {
        return this.scores.nextFrame();
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    public void add(int numberOfPin) {
        this.scores.checkBeforeAddNormal(numberOfPin);
        this.scores.add(new Score(numberOfPin));
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "scores=" + scores +
                '}';
    }

    public void add(Scores scores) {
        this.scores = scores;
    }
}
