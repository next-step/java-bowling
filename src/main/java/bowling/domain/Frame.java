package bowling.domain;

public interface Frame {
    boolean end();

    void addScore(Score score);

    Scores getScores();

    FrameResult getResult();
}
