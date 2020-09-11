package bowling;

import bowling.domain.Frames;
import bowling.domain.Result;
import bowling.view.InputScanner;
import bowling.view.ResultViewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String name = InputScanner.getName("플레이어 이름은(3 english letters)?: ");
        Frames frames = Frames.from();
        Map<Integer, List<Result>> score = new HashMap<>();

        while (!frames.isFinish()) {
            int number = frames.getNumber();
            int hitCount = InputScanner.getHitCount(String.format("%s프레임 투구 : ", number));

            if (!score.containsKey(number)){
                score.put(number, new ArrayList<>());
            }

            score.get(number).add(frames.hit(hitCount));

            ResultViewer.showResult(10, name, score);
        }
    }
}
