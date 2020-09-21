package bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Scores {
    private List<Score> scores = new ArrayList<>();

    private Scores(){}

    public static Scores init(){
        return new Scores();
    }
    public void add(Score score){
        scores.add(score);
    }

    @Override
    public String toString(){
        String str = scores.isEmpty() ? "" :
                scores.stream()
                .map(Score::toString)
                .collect(Collectors.joining(" | "));

        return String.format("%-7.7s", str);
    }

    public int getSize(){
        return scores.size();
    }
}
