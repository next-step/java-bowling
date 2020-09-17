package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("프레임 투구 객체 테스트")
public class NormalFrameBowlTest {

    @DisplayName("투구 테스트: 스트라이크")
    @Test
    public void bowl_strike() {
        FrameBowl frameBowl = new FrameBowl();
        assertThat(frameBowl.bowl(10)).isEqualTo(BowlResult.STRIKE);
    }

    @DisplayName("투구 테스트: 스페어")
    @Test
    public void bowl_spare() {
        FrameBowl frameBowl = new FrameBowl();
        assertThat(frameBowl.bowl(9)).isEqualTo(BowlResult.NONE);
        assertThat(frameBowl.bowl(1)).isEqualTo(BowlResult.SPARE);
    }

    @DisplayName("투구 테스트: 미스")
    @Test
    public void bowl_miss() {
        FrameBowl frameBowl = new FrameBowl();
        assertThat(frameBowl.bowl(9)).isEqualTo(BowlResult.NONE);
        assertThat(frameBowl.bowl(0)).isEqualTo(BowlResult.MISS);
    }

    @DisplayName("투구 테스트: 거터")
    @Test
    public void bowl_gutter() {
        FrameBowl frameBowl = new FrameBowl();
        assertThat(frameBowl.bowl(0)).isEqualTo(BowlResult.NONE);
        assertThat(frameBowl.bowl(0)).isEqualTo(BowlResult.GUTTER);
    }

    @DisplayName("투구 유효성 테스트: 0보다 작거나 10보다 큰 수 입력")
    @ParameterizedTest
    @ValueSource(ints = { -5, 12 })
    public void validate_bowl(int numberOfPins) {
        FrameBowl frameBowl = new FrameBowl();
        assertThatIllegalArgumentException().isThrownBy(() -> frameBowl.bowl(numberOfPins));
    }

    @DisplayName("투구 유효성 테스트: 3번 투구")
    @Test
    public void validate_bowlCount() {
        FrameBowl frameBowl = new FrameBowl();
        assertThatIllegalArgumentException().isThrownBy(() -> {
            frameBowl.bowl(5);
            frameBowl.bowl(2);
            frameBowl.bowl(1);
        });
    }

    @DisplayName("투구 유효성 테스트: 핀갯수가 10을 초과")
    @Test
    public void validate_totalNumberOfPins() {
        FrameBowl frameBowl = new FrameBowl();
        assertThatIllegalArgumentException().isThrownBy(() -> {
            frameBowl.bowl(8);
            frameBowl.bowl(3);
        });
    }

    @DisplayName("투구 수 테스트")
    @Test
    public void getBowlCount() {
        FrameBowl frameBowl = new FrameBowl();
        frameBowl.bowl(5);
        assertThat(frameBowl.getBowlCount()).isEqualTo(1);
    }

    @DisplayName("전체 핀 갯수 테스트")
    @Test
    public void getTotalNumberOfPins() {
        FrameBowl frameBowl = new FrameBowl();
        frameBowl.bowl(5);
        frameBowl.bowl(3);
        assertThat(frameBowl.getTotalNumberOfPins()).isEqualTo(8);
    }

}
