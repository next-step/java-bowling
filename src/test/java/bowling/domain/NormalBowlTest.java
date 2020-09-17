package bowling.domain;

import bowling.domain.bowl.NormalBowl;
import bowling.domain.bowl.NormalBowlResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("프레임 투구 객체 테스트")
public class NormalBowlTest {

    @DisplayName("투구 테스트: 스트라이크")
    @Test
    public void bowl_strike() {
        NormalBowl normalBowl = new NormalBowl();
        assertThat(normalBowl.bowl(10)).isEqualTo(NormalBowlResult.STRIKE);
    }

    @DisplayName("투구 테스트: 스페어")
    @Test
    public void bowl_spare() {
        NormalBowl normalBowl = new NormalBowl();
        assertThat(normalBowl.bowl(9)).isEqualTo(NormalBowlResult.DEFAULT);
        assertThat(normalBowl.bowl(1)).isEqualTo(NormalBowlResult.SPARE);
    }

    @DisplayName("투구 테스트: 미스")
    @Test
    public void bowl_miss() {
        NormalBowl normalBowl = new NormalBowl();
        assertThat(normalBowl.bowl(9)).isEqualTo(NormalBowlResult.DEFAULT);
        assertThat(normalBowl.bowl(0)).isEqualTo(NormalBowlResult.MISS);
    }

    @DisplayName("투구 테스트: 거터")
    @Test
    public void bowl_gutter() {
        NormalBowl normalBowl = new NormalBowl();
        assertThat(normalBowl.bowl(0)).isEqualTo(NormalBowlResult.DEFAULT);
        assertThat(normalBowl.bowl(0)).isEqualTo(NormalBowlResult.GUTTER);
    }

    @DisplayName("투구 유효성 테스트: 0보다 작거나 10보다 큰 수 입력")
    @ParameterizedTest
    @ValueSource(ints = { -5, 12 })
    public void validate_bowl(int numberOfPins) {
        NormalBowl normalBowl = new NormalBowl();
        assertThatIllegalArgumentException().isThrownBy(() -> normalBowl.bowl(numberOfPins));
    }

    @DisplayName("투구 유효성 테스트: 3번 투구")
    @Test
    public void validate_bowlCount() {
        NormalBowl normalBowl = new NormalBowl();
        assertThatIllegalArgumentException().isThrownBy(() -> {
            normalBowl.bowl(5);
            normalBowl.bowl(2);
            normalBowl.bowl(1);
        });
    }

    @DisplayName("투구 유효성 테스트: 핀갯수가 10을 초과")
    @Test
    public void validate_totalNumberOfPins() {
        NormalBowl normalBowl = new NormalBowl();
        assertThatIllegalArgumentException().isThrownBy(() -> {
            normalBowl.bowl(8);
            normalBowl.bowl(3);
        });
    }

    @DisplayName("투구 수 테스트")
    @Test
    public void getBowlCount() {
        NormalBowl normalBowl = new NormalBowl();
        normalBowl.bowl(5);
        assertThat(normalBowl.getBowlCount()).isEqualTo(1);
    }

    @DisplayName("전체 핀 갯수 테스트")
    @Test
    public void getTotalNumberOfPins() {
        NormalBowl normalBowl = new NormalBowl();
        normalBowl.bowl(5);
        normalBowl.bowl(3);
        assertThat(normalBowl.getTotalNumberOfPins()).isEqualTo(8);
    }

}
