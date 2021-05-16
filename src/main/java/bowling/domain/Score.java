package bowling.domain;

public class Score {

    private static final int INIT_SCORE = 0;
    private static final boolean INIT_ENDED_SCORING = false;
    private static final boolean ENDED_SCORING = true;

    private int score;
    private boolean endedScoring;

    public Score(){
        score = INIT_SCORE;
        endedScoring = INIT_ENDED_SCORING;
    }

    public void addScore(int score){
        this.score+=score;
    }

    public void endScoring(){
        this.endedScoring = ENDED_SCORING;
    }
}
