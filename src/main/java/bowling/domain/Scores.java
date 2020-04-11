package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Scores {
    private List<Score> scores = new ArrayList<>();
    private Boolean continueFlag = true;

    public Scores(Score score) {
        this.continueFlag = !score.isMaxScore();
        this.scores.add(score);
    }

    public Boolean getContinueFlag() {
        return continueFlag;
    }

    public void add(Score score){
        this.scores.add(score);
        this.continueFlag = false;
        score.checkScore(scores);
    }

    public int size() {
        return scores.size();
    }
}
