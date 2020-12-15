package bowling.model;

import java.util.List;

public class Scores {

    private List<Integer> scores;

    private Scores(List<Integer> scores){
        this.scores = scores;
    }

    public static Scores from(List<Integer> scores){
        return new Scores(scores);
    }
}
