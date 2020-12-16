package bowling.domain.bowlinggame;

import bowling.domain.PlayerName;
import bowling.domain.frames.FrameViewDto;

import java.util.stream.Stream;

public interface BowlingGameViewDto {
    Stream<FrameViewDto> framesViewDtoStream();

    PlayerName getPlayerName();
}
