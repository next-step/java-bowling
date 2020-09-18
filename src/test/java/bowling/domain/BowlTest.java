package bowling.domain;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.BowlStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("프레임 투구 객체 테스트")
public class BowlTest {

    @DisplayName("투구 테스트: 스트라이크")
    @Test
    public void bowl_strike() {
        Bowl bowl = new Bowl();
        bowl.bowl(10);
        assertThat(bowl.getBowlStatus()).isEqualTo(BowlStatus.STRIKE);
    }

    @DisplayName("투구 테스트: 스페어")
    @Test
    public void bowl_spare() {
        Bowl bowl = new Bowl();
        bowl.bowl(9);
        bowl.bowl(1);
        assertThat(bowl.getBowlStatus()).isEqualTo(BowlStatus.SPARE);
    }

    @DisplayName("투구 테스트: 미스")
    @Test
    public void bowl_miss() {
        Bowl bowl = new Bowl();
        bowl.bowl(9);
        bowl.bowl(0);
        assertThat(bowl.getBowlStatus()).isEqualTo(BowlStatus.MISS);
    }

    @DisplayName("투구 테스트: 거터")
    @Test
    public void bowl_gutter() {
        Bowl bowl = new Bowl();
        bowl.bowl(0);
        bowl.bowl(0);
        assertThat(bowl.getBowlStatus()).isEqualTo(BowlStatus.GUTTER);
    }

    @DisplayName("투구 유효성 테스트: 0보다 작거나 10보다 큰 수 입력")
    @ParameterizedTest
    @ValueSource(ints = { -5, 12 })
    public void validate_bowl(int numberOfPins) {
        Bowl bowl = new Bowl();
        assertThatIllegalArgumentException().isThrownBy(() -> bowl.bowl(numberOfPins));
    }

    @DisplayName("투구 유효성 테스트: 3번 투구")
    @Test
    public void validate_bowlCount() {
        Bowl bowl = new Bowl();
        assertThatIllegalArgumentException().isThrownBy(() -> {
            bowl.bowl(5);
            bowl.bowl(2);
            bowl.bowl(1);
        });
    }

    @DisplayName("투구 유효성 테스트: 핀갯수가 10을 초과")
    @Test
    public void validate_totalNumberOfPins() {
        Bowl bowl = new Bowl();
        assertThatIllegalArgumentException().isThrownBy(() -> {
            bowl.bowl(8);
            bowl.bowl(3);
        });
    }

    @DisplayName("투구 수 테스트")
    @Test
    public void getBowlCount() {
        Bowl bowl = new Bowl();
        bowl.bowl(5);
        assertThat(bowl.getBowlCount()).isEqualTo(1);
    }

    @DisplayName("전체 핀 갯수 테스트")
    @Test
    public void getTotalNumberOfPins() {
        Bowl bowl = new Bowl();
        bowl.bowl(5);
        bowl.bowl(3);
        assertThat(bowl.getTotalNumberOfPin()).isEqualTo(8);
    }

}
