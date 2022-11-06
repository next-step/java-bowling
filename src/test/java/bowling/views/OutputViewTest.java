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
                        new BowlingGameFrameDto(List.of(10), true),
                        new BowlingGameFrameDto(List.of(1, 9), true),
                        new BowlingGameFrameDto(List.of(2, 7), true),
                        new BowlingGameFrameDto(List.of(3, 0), true),
                        new BowlingGameFrameDto(List.of(4, 1), true),
                        new BowlingGameFrameDto(List.of(5, 0), true),
                        new BowlingGameFrameDto(List.of(6, 1), true),
                        new BowlingGameFrameDto(List.of(7, 3), true),
                        new BowlingGameFrameDto(List.of(8, 2), true),
                        new BowlingGameFrameDto(List.of(1, 9, 10), true)
                )
        );
        OutputView.printBowlingGame("PJS", game);
    }

}
