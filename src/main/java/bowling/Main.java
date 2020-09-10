package bowling;

import bowling.domain.Bowler;
import bowling.view.InputScanner;
import bowling.view.Printer;

public class Main {
    public static void main(String[] args) {
        Bowler bowler = InputScanner.getBowler("플레이어 이름은(3 english letters)?: ");

        while (bowler.isPlaying()) {
            int hitCount = InputScanner.getHitCount(String.format("%s프레임 투구 : ", bowler.getPlayStage()));
            bowler.record(hitCount);

            Printer.showResult(bowler);
        }
    }
}
