package bowling.step3.dto;

import bowling.step3.domain.Frame;

public class FrameDto {
    public final ScoreDto score;
    public final PinCountDto pinCounts;

    public FrameDto(ScoreDto score, PinCountDto pintCounts) {
        this.score = score;
        this.pinCounts = pintCounts;
    }

    public static FrameDto from(Frame frame) {
        if(frame == null){
            System.out.println("hi~~");
        }
        ScoreDto scoreDto = ScoreDto.from(frame.score());
        PinCountDto pinCountDto = PinCountDto.from(frame.pitches());
        return new FrameDto(scoreDto, pinCountDto);
    }
}
