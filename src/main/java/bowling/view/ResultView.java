package bowling.view;

import bowling.domain.BowlingGames;
import bowling.dto.BowlingGameDto;
import bowling.dto.BowlingGamesDto;

public interface ResultView {
    void print(BowlingGameDto bowlingGameDto);

    void print2(BowlingGamesDto bowlingGamesDto);
}
