package bowling.domain.bowlinggame;

import bowling.domain.PlayerName;
import bowling.domain.frames.Frame;

import java.util.stream.Stream;

public interface BowlingGameViewDto {
    Stream<Frame> framesStream();

    PlayerName getPlayerName();
}
