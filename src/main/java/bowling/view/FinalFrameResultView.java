package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Pitches;

import java.util.stream.IntStream;

public class FinalFrameResultView extends ResultView implements ResultViewType {
    @Override
    public String frameResult(Frame frame) {
        Pitches pitches = frame.pitches();
        return pitchesToString(pitches);
    }

    private String pitchesToString(Pitches pitches) {
        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(1, pitches.count())
                .forEach(index -> builder.append(pitchToString(pitches.get(index - 1).intValue(),
                        index,
                        pitches.sum(index),
                        pitches.get(0).intValue())));
        return builder.toString();
    }

    private String pitchToString(int fallPins, int index, int sum, int firstPitchFallPins) {
        StringBuilder builder = new StringBuilder();
        if (index > 1) {
            builder.append(DELIMITER);
        }
        if (index == 1 && fallPins == 10) {
            return builder.append("X").toString();
        }
        if (index == 2 && sum == 10 && firstPitchFallPins != 10) {
            return builder.append("/").toString();
        }
        if (index == 2 && sum == 20) {
            return builder.append("X").toString();
        }
        if (index == 3 && sum == 20 && firstPitchFallPins != 10) {
            return builder.append("X").toString();
        }
        if (index == 3 && sum == 20) {
            return builder.append("/").toString();
        }
        if (sum == 30) {
            return builder.append("X").toString();
        }
        return builder.append(convertZeroToHyphen(fallPins)).toString();
    }
}
