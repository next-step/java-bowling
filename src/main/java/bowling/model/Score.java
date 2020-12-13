package bowling.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {
    private final static String SCORE_ERROR = "허용할 수 없는 값입니다.";
    private final static int MIN_SCORE = 0;
    private final static int MAX_SCORE = 10;

    private final static Map<Integer, Score> cache;
    private final int score;

    static {
        cache = IntStream.rangeClosed(MIN_SCORE, MAX_SCORE)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), Score::new));
    }

    private Score(int fallenPin){
        this.score = fallenPin;
    }

    public static Score from(int fallenPin){
        validScore(fallenPin);
        return cache.get(fallenPin);
    }

    public Score add(int fallenPin){
        int totalScore = this.score + fallenPin;
        validScore(totalScore);

        return cache.get(totalScore);
    }

    public boolean isMaxScore(){
        return score == MAX_SCORE;
    }

    public boolean isMinScore(){
        return score == MIN_SCORE;
    }

    private static void validScore(int fallenPin) {
        if(!cache.containsKey(fallenPin)){
            throw new IllegalArgumentException(SCORE_ERROR);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }
}
