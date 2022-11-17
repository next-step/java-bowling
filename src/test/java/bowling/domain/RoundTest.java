package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoundTest {

    @Test
    @DisplayName("초기 상태 테스트")
    void round_init() {
        Round round = new Round();

        assertThat(round.isEnd()).isFalse();
        assertThat(round.getCurrentFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크 투구 테스트")
    void round_hit() {
        Round round = new Round();
        round.hit(new Hit(10));
        round.hit(new Hit(5));

        assertThat(round.isEnd()).isFalse();
        assertThat(round.getCurrentFrameNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("라운드 종료 테스트")
    void round_end() {
        Round round = new Round();
        round.hit(new Hit(10));
        round.hit(new Hit(10));
        round.hit(new Hit(10));
        round.hit(new Hit(10));
        round.hit(new Hit(10));
        round.hit(new Hit(10));
        round.hit(new Hit(10));
        round.hit(new Hit(10));
        round.hit(new Hit(10));
        round.hit(new Hit(5));
        round.hit(new Hit(4));

        assertThat(round.isEnd()).isTrue();
        assertThat(round.getCurrentFrameNumber()).isEqualTo(10);
    }
}
