package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame extends NormalFrame {

    private FinalFrame(List<ShotScore> shotScores, boolean hasBonus) {
        super(shotScores, hasBonus);
    }

    public static Frame of() {
        return new FinalFrame(new ArrayList<>(), true);
    }

}
