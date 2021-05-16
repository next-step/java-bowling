package bowling.domain;

import bowling.domain.frame.Frames;

public class Bowling implements Playable{

    private static final int INIT_SCORE = 0;

    private int score;
    private Frames frames;

    public Bowling(){
        score = INIT_SCORE;
        frames = new Frames();
    }

    @Override
    public void play(int point) {
        frames.bowl(point);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
