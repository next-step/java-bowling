package bowling.step4.dto;

import bowling.step4.domain.Frame;

public class FrameDto {
    public final ScoreDto score;
    public final PinCountDto pinCounts;

    public FrameDto(ScoreDto score, PinCountDto pintCounts) {
        this.score = score;
        this.pinCounts = pintCounts;
    }

    public static FrameDto from(Frame frame) {
        ScoreDto scoreDto = ScoreDto.from(frame.score());
        PinCountDto pinCountDto = PinCountDto.from(frame.pitches());
        return new FrameDto(scoreDto, pinCountDto);
    }
}
