package bowling.domain;


import bowling.domain.ScoreUI.ScoreFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingResult {

    private List<ScoreFactory> result = new ArrayList<>();

    public void addScoreUI(ScoreFactory scoreFactory) {
        System.out.println(scoreFactory.getString());
        result.add(scoreFactory);
    }

    public List<ScoreFactory> getResultUI() {
        return Collections.unmodifiableList(result);
    }
}
