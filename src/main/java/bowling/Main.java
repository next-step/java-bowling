package bowling;

import bowling.domain.Frames;
import bowling.view.InputScanner;
import bowling.view.ResultViewer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String name = InputScanner.getName("플레이어 이름은(3 english letters)?: ");
        Frames frames = Frames.from();
        List<Integer> score = new ArrayList<>();

        while (!frames.isFinish()) {
            int hitCount = InputScanner.getHitCount(String.format("%s프레임 투구 : ", frames.getNumber()));
            score.add(hitCount);
            frames.hit(hitCount);
            ResultViewer.showResult(10, name, score);
        }
    }
}
