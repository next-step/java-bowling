package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("점수 테스트")
public class ScoreTest {

    private Score score;

    @BeforeEach
    void setUp() {
        score = new Score(new NormalFrame());
    }

    @DisplayName("[일반 프레임] 투구가 완료되지 않은 경우 진행상태이다.")
    @Test
    void progress() {
        score.addPin(Pin.of(5));

        assertThat(score.status()).isEqualTo(ScoreType.PROCEEDING);
    }

    @DisplayName("[일반 프레임] 첫 번째 투구에서 모든 핀을 쓰러뜨린 경우 스트라이크이다.")
    @Test
    void strike() {
        score.addPin(Pin.of(10));

        assertThat(score.status()).isEqualTo(ScoreType.STRIKE);
    }

    @DisplayName("[일반 프레임] 두 번째 투구에서 모든 핀을 쓰러뜨린 경우 스패어이다.")
    @Test
    void spare() {
        score.addPin(Pin.of(5));
        score.addPin(Pin.of(5));

        assertThat(score.status()).isEqualTo(ScoreType.SPARE);
    }

    @DisplayName("[일반 프레임] 두 번째 투구에서도 모든 핀이 쓰러지지 않은 경우 미스이다.")
    @Test
    void miss() {
        score.addPin(Pin.of(1));
        score.addPin(Pin.of(5));

        assertThat(score.status()).isEqualTo(ScoreType.MISS);
    }

    @DisplayName("[일반 프레임] 두 번째 투구에서 핀의 합이 10을 넘은 경우 예외가 발생한다.")
    @Test
    void exception() {
        score.addPin(Pin.of(5));

        assertThatThrownBy(() -> score.addPin(Pin.of(10)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
