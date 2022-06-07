package bowling.controller;

import bowling.domain.FinalFrame;
import bowling.domain.NormalFrame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private static final int FINAL_INDEX = 10;

    public static void start() {
        List<String> results = new ArrayList<>();
        String name = InputView.inputNames();
        ResultView.drawName(name);
        for (int i = 1; i < 11; i++) {
            playFrame(name, i, results);
        }
    }

    private static void playFrame(String name, int index, List<String> results) {
        NormalFrame normalFrame = NormalFrame.first(InputView.inputPitchingNumber(index));
        results.add(ResultView.selectNormalResult(normalFrame));
        ResultView.drawOneFrame(name, results);
        if (!normalFrame.isStrike()) {
            normalFrame = normalFrame.second(InputView.inputPitchingNumber(index));
            results.remove(results.size() - 1);
            results.add(ResultView.selectNormalResult(normalFrame));
            ResultView.drawOneFrame(name, results);
        }
    }
}
