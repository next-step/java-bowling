package bowling.domain.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameNumericScores {

    private final List<FrameNumericScore> frameNumericScores = new ArrayList<>();

    public void add(FrameNumericScore firstFrameScore) {
        if (firstFrameScore != null) {
            frameNumericScores.add(firstFrameScore);
        }
    }

    public List<FrameNumericScore> getFrameNumericScores() {
        return Collections.unmodifiableList(frameNumericScores);
    }

    public List<Integer> getScores() {
        List<Integer> returnList = new ArrayList<>();
        returnList.add(frameNumericScores.get(0).getFrameScoreTotal());
        for (int i = 1; i < frameNumericScores.size(); i++) {
            returnList.add(returnList.get(i - 1) + frameNumericScores.get(i).getFrameScoreTotal());
        }
        return returnList;
    }
}
