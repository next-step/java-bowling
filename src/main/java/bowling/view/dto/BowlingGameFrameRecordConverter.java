package bowling.view.dto;

import static java.util.stream.Collectors.*;

import java.util.List;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Score;

public class BowlingGameFrameRecordConverter {
    public static BowlingGameFrameRecord convertEmptyRecord() {
        return convert(Score.needToMoreBowl(), List.of());
    }

    public static List<BowlingGameFrameRecord> convert(List<Frame> frames) {
        return frames.stream()
            .map(BowlingGameFrameRecordConverter::convert)
            .collect(toList());
    }

    private static BowlingGameFrameRecord convert(Frame frame) {
        return convert(frame.getScore(), BowlRecordConverter.convert(frame.getStates()));
    }

    private static BowlingGameFrameRecord convert(Score score, List<BowlRecord> bowlRecords) {
        ScoreDto scoreDto = ScoreDtoConverter.convert(score);
        return new BowlingGameFrameRecord(scoreDto, bowlRecords);
    }
}
