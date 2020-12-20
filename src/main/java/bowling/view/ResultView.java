package bowling.view;

import bowling.domain.BowlingGames;
import bowling.dto.BowlingGameDto;

public interface ResultView {
    void print(BowlingGameDto bowlingGameDto);

    void print2(BowlingGames bowlingGames);
}
