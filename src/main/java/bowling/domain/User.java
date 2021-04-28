package bowling.domain;

public class User {

    private final String name;
    private final Scores scores;

    public User(String name) {
        this.name = name;
        this.scores = new Scores();
    }

    public String name() {
        return name;
    }

    public int getScore(FrameStrategy frame) {
        return scores.score(frame);
    }

    public void recordScore(FrameStrategy frame, FrameStrategy previousFrame) {
        scores.record(frame, previousFrame);
    }

    public int scoreSize() {
        return scores.size();
    }
}
