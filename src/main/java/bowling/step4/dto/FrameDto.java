package bowling.step4.dto;

import bowling.step4.domain.Frame;

import java.util.List;
import java.util.stream.Collectors;

public class FrameDto {
    public final ScoreDto score;
    public final List<PinCountDto> pinCounts;

    public FrameDto(ScoreDto score, List<PinCountDto> pintCounts) {
        this.score = score;
        this.pinCounts = pintCounts;
    }

    public static FrameDto from(Frame frame) {
        ScoreDto scoreDto = ScoreDto.from(frame.score());
        List<PinCountDto> pinCountDto = frame.pitches().value().stream().map(it-> PinCountDto.from(it)).collect(Collectors.toList());
        return new FrameDto(scoreDto, pinCountDto);
    }
}
