package bowling.views;

import bowling.dto.BowlingGameDto;
import bowling.dto.BowlingGameFrameDto;
import org.junit.jupiter.api.Test;

import java.util.List;

class OutputViewTest {

    @Test
    void printBowlingGame() {
        BowlingGameDto game = new BowlingGameDto(
                List.of(
                        new BowlingGameFrameDto(List.of(10)),
                        new BowlingGameFrameDto(List.of(1, 9)),
                        new BowlingGameFrameDto(List.of(2, 7)),
                        new BowlingGameFrameDto(List.of(3, 0)),
                        new BowlingGameFrameDto(List.of(4, 1)),
                        new BowlingGameFrameDto(List.of(5, 0)),
                        new BowlingGameFrameDto(List.of(6, 1)),
                        new BowlingGameFrameDto(List.of(7, 3)),
                        new BowlingGameFrameDto(List.of(8, 2)),
                        new BowlingGameFrameDto(List.of(1, 9, 10))
                )
        );
        OutputView.printBowlingGame("PJS", game);
    }

}
