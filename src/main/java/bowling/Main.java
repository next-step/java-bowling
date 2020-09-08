package bowling;

import bowling.domain.FrameSet;
import bowling.view.InputScanner;
import bowling.view.Printer;

public class Main {
    public static void main(String[] args) {
        String bowler = InputScanner.getBowler("플레이어 이름은(3 english letters)?: ");

        FrameSet frameSet = FrameSet.from(bowler);

        while (frameSet.isPlaying()) {
            int hitCount = InputScanner.getHitCount(String.format("%s프레임 투구 : ", frameSet.getPlayStage()));
            frameSet.record(hitCount);

            Printer.showResult(frameSet);
        }
    }
}
