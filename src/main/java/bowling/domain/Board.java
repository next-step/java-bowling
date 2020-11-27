package bowling.domain;

class Board {
    private final Frames frames = new Frames();
    private final Scores scores = new Scores();

    void update(Rolls rolls) {
        frames.update(rolls);
        scores.update(rolls, frames);
    }
}
