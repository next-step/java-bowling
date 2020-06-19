package bowling.domain;

public interface Frame {

    public void bowl(int hitCounts);

    public Frame next();

    public int getScore();

    public PitchesGroup getPitchesGroup();
}
