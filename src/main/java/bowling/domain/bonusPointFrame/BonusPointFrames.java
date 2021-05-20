//package bowling.domain.bonusPointFrame;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class BonusPointFrames {
//    private List<BonusPointFrame> bonusPointFrames;
//
//    public BonusPointFrames() {
//        bonusPointFrames = new ArrayList<>();
//    }
//
//    public void addBonusPoints(int point) {
//        for (BonusPointFrame bonusPointFrame : bonusPointFrames) {
//            bonusPointFrame.addBonusPoint(point);
//        }
//    }
//
//    public void update() {
//        List<BonusPointFrame> outdated = bonusPointFrames.stream()
//                .filter(bonusPointFrame -> !bonusPointFrame.needMoreBonus())
//                .collect(Collectors.toList());
//        for (BonusPointFrame bonusPointFrame : outdated) {
//            bonusPointFrame.endScoring();
//        }
//
//        bonusPointFrames = bonusPointFrames.stream()
//                .filter(BonusPointFrame::needMoreBonus)
//                .collect(Collectors.toList());
//    }
//
//    public void addBonusPointFrame(BonusPointFrame bonusPointFrame) {
//        bonusPointFrames.add(bonusPointFrame);
//    }
//}
