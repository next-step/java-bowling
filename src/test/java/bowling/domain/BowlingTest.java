package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by seungwoo.song on 2022-12-01
 */
class BowlingTest {

    @Test
    void 게임실행1() {
        Bowling bowling = new Bowling();
        bowling.bowl(PinCount.of(10));
        bowling.bowl(PinCount.of(1));
        bowling.bowl(PinCount.of(1));

        assertThat(bowling.createResults()).hasSize(2);
    }

    @Test
    void 게임실행2() {
        Bowling bowling = new Bowling();
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);

        List<Result> results = bowling.createResults();
        //results.forEach(System.out::println);
        assertThat(results).hasSize(10);
    }

    @Test
    void 게임실행3() {
        Bowling bowling = new Bowling();
        bowling.bowl(1);
        bowling.bowl(2);

        bowling.bowl(5);
        bowling.bowl(5);

        bowling.bowl(0);
        bowling.bowl(10);

        bowling.bowl(10);

        bowling.bowl(3);
        bowling.bowl(3);

        bowling.bowl(4);
        bowling.bowl(2);

        bowling.bowl(10);

        bowling.bowl(10);

        bowling.bowl(10);

        bowling.bowl(2);
        bowling.bowl(3);

        List<Result> results = bowling.createResults();
        results.forEach(System.out::println);
        assertThat(results).hasSize(10);
    }
}