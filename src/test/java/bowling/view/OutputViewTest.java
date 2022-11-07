package bowling.view;

import bowling.domain.Pin;
import bowling.domain.Player;
import bowling.domain.PlayerName;
import bowling.dto.BowlingRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class OutputViewTest {

    @Test
    @DisplayName("게임 출력 확인")
    void print_bowlingGame() {
        Player pls = new Player(new PlayerName("pls"));
        pls.play(a -> new Pin(10));
        pls.next();
        pls.play(a -> new Pin(9));
        pls.play(a -> new Pin(1));
        pls.next();
        pls.play(a -> new Pin(0));
        pls.play(a -> new Pin(5));
        pls.next();

        pls.play(a -> new Pin(4));
        pls.play(a -> new Pin(4));
        pls.next();
        pls.play(a -> new Pin(4));
        pls.play(a -> new Pin(4));
        pls.next();
        pls.play(a -> new Pin(4));
        pls.play(a -> new Pin(4));
        pls.next();
        pls.play(a -> new Pin(4));
        pls.play(a -> new Pin(4));
        pls.next();
        pls.play(a -> new Pin(4));
        pls.play(a -> new Pin(4));
        pls.next();

        pls.play(a -> new Pin(4));
        pls.play(a -> new Pin(4));
        pls.next();
        pls.play(a -> new Pin(4));
        pls.play(a -> new Pin(4));

        List<BowlingRecord> list = new ArrayList<>();
        list.add(BowlingRecord.of(pls));
        OutputView.print(list);
    }

    @Test
    @DisplayName("퍼펙트 게임 출력")
    void print_perfectGame() {
        Player pls = new Player(new PlayerName("pls"));

        IntStream.range(0, 12)
                .forEach(i -> {
                    pls.play(range -> new Pin(10));
                    if (!pls.isFinished()) {
                        pls.next();
                    }
                });

        List<BowlingRecord> list = new ArrayList<>();
        list.add(BowlingRecord.of(pls));
        OutputView.print(list);
    }


}