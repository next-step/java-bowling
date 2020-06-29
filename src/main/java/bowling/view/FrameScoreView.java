package bowling.view;

import bowling.domain.ScoreType;
import bowling.domain.frame.FrameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FrameScoreView {

     void printScores(List<Optional<Integer>> scores) {
        StringBuilder scoreDisplays = new StringBuilder();
        scoreDisplays.append("|      |");
        int sum = 0;
        for (Optional<Integer> score : scores) {
            sum += score.orElse(0);
            scoreDisplays.append(String.format("  %-4s|", score.isPresent() ? sum : ""));
        }
        System.out.println(scoreDisplays.toString());
    }

}
