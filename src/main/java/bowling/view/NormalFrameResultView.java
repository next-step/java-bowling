package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Pitches;

import java.util.stream.IntStream;

public class NormalFrameResultView extends ResultView implements ResultViewType {
    @Override
    public String frameResult(Frame frame) {
        Pitches pitches = frame.pitches();
        return pitchesToString(pitches);
    }

    private String pitchesToString(Pitches pitches) {
        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(1, pitches.count())
                .forEach(index -> builder.append(pitchToString(pitches.get(index - 1).intValue(), index, pitches.sum())));
        return builder.toString();
    }

    private String pitchToString(int fallPins, int index, int sum) {
        StringBuilder builder = new StringBuilder();
        if (index > 1) {
            builder.append(DELIMITER);
        }
        if (index == 1 && fallPins == 10) {
            return builder.append(SYMBOL_STRIKE).toString();
        }
        if (index == 2 && sum == 10) {
            return builder.append(SYMBOL_SPARE).toString();
        }
        return builder.append(convertZeroToHyphen(fallPins)).toString();
    }
}
