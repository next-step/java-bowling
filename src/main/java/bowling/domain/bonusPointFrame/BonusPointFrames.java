package bowling.domain.bonusPointFrame;

import java.util.List;
import java.util.stream.Collectors;

public class BonusPointFrames {
    private List<BonusPointFrame> bonusPointFrames;

    public void addBonusPoints(int point) {
        for (BonusPointFrame bonusPointFrame : bonusPointFrames) {
            bonusPointFrame.addBonusPoint(point);
        }
    }

    public void update() {
        bonusPointFrames = bonusPointFrames.stream()
                .filter(BonusPointFrame::needMoreBonus)
                .collect(Collectors.toList());
    }

}
