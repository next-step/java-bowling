package bowling.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {

    private static Players players;

    @BeforeAll
    static void setUp() {
        players = new Players
                .Builder(() -> 1)
                .withPlayerNames(value -> "PJT")
                .build();
    }

    @Test
    @DisplayName("정상 실행 (모두 스트라이크)")
    void testBowlingAllStrike() {
        // given
        List<Integer> rolls = IntStream
                .range(0, 12)
                .mapToObj(value -> 10)
                .collect(Collectors.toList());
        Iterator<Integer> itr = rolls.iterator();

        ToIntFunction<Player> rollFunction = value -> itr.next();

        Bowling bowling = new Bowling
                .Builder(players, rollFunction)
                .build();
        // when
        bowling.start();
        // then
        assertThat(bowling.isAllLaneEnd()).isTrue();
    }

    @Test
    @DisplayName("정상 실행 (모두 오픈)")
    void testBowlingAllOpen() {
        // given
        List<Integer> rolls = IntStream
                .range(0, 20)
                .mapToObj(value -> 3)
                .collect(Collectors.toList());
        Iterator<Integer> itr = rolls.iterator();

        ToIntFunction<Player> rollFunction = value -> itr.next();

        Bowling bowling = new Bowling
                .Builder(players, rollFunction)
                .build();
        // when
        bowling.start();
        // then
        assertThat(bowling.isAllLaneEnd()).isTrue();
    }
}
