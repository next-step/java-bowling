package bowling;

import java.util.HashMap;
import java.util.Map;

public class Score {
    private static final Map<Integer, Score> SCORE_MAP = new HashMap<>();
    private static final int MIN_SCORE = 0;
    private static final int MAX_SCORE = 10;

    private final int score;
    private boolean isSpare;

    private Score(int score) {
        if(score < MIN_SCORE || score > MAX_SCORE) throw new IllegalArgumentException("Invalid score");
        this.score = score;
    }

    private Score(int score, boolean isSpare) {
        this(score);
        this.isSpare = isSpare;
    }

    public static Score of(int score, boolean isSpare){
        return new Score(score, isSpare);
    }

    @Override
    public String toString() {
        return parse(score);
    }

    private String parse(int score){
        if(isSpare){
            return "/";
        }
        switch(score){
            case 10:
                return "X";
            case 9:
            case 8:
            case 7:
            case 6:
            case 5:
            case 4:
            case 3:
            case 2:
            case 1:
                return String.valueOf(score);
            case 0:
                return "-";
            default:
                throw new IllegalArgumentException("Invalid score");
        }
    }
}
