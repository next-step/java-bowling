package bowling.view;

import bowling.domain.Pin;
import bowling.domain.PlayerName;
import bowling.domain.dto.BowlingRecord;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

class OutputViewTest {

    @Test
    @DisplayName("게임 출력 확인")
    void print_bowlingGame() {
        Frames frames = new Frames();
        frames.bowl(a -> new Pin(10));
        frames.bowl(a -> new Pin(9));
        frames.bowl(a -> new Pin(1));
        frames.bowl(a -> new Pin(0));
        frames.bowl(a -> new Pin(5));

        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));

        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));

        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));
        frames.bowl(a -> new Pin(4));
        BowlingRecord pjs = BowlingRecord.of(frames, new Pin(4), new PlayerName("PJS"));
        OutputView.print(pjs);
    }

    @Test
    @DisplayName("퍼펙트 게임 출력")
    void print_perfectGame() {
        Frames frames = new Frames();
        IntStream.range(0, 11)
                .forEach(i -> frames.bowl(range -> new Pin(10)));
        OutputView.print(BowlingRecord.of(frames, new Pin(10), new PlayerName("PJS")));
    }


}