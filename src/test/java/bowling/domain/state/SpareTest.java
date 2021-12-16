package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

class SpareTest {

    //TODO bowl 메서드가 어느 '상황'에서 예외가 발생하는지도 적어주시면 좋을듯
    @DisplayName("bowl() 메서드는 UnsupportOpeationException 반환한다.")
    @Test
    void bowlFailTest() {
        Spare spare = new Spare(Pin.from(5), Pin.from(5));
        assertThatThrownBy(() ->  spare.bowl(Pin.TEN)).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        Spare spare = new Spare(Pin.from(5), Pin.from(5));
        assertThat(spare).isEqualTo(new Spare(Pin.from(5), Pin.from(5)));
    }

    @DisplayName("쓰러트린 갯수가 10보다 작으면 illegal Exception")
    @Test
    void createFailTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Spare(Pin.from(5), Pin.from(4)));
    }

    @DisplayName("score()은 Pin의 갯수의 합과 시도횟수 1번을 반환한다..")
    @Test
    void scoreTest() {
        assertThat(new Spare(Pin.from(5), Pin.from(5)).score()).isEqualTo(Score.of(10, 1));
    }

    @DisplayName("calculateAdditionalScore() left에 따라 스코어를 반환한다.")
    @Test
    void calculateAdditionalScoreTest() {
        assertThat(new Spare(Pin.from(4), Pin.from(6)).calculateAdditionalScore(Score.of(5, 2)))
                .isEqualTo(Score.of(15, 0));
        assertThat(new Spare(Pin.from(4), Pin.from(6)).calculateAdditionalScore(Score.of(5, 1)))
                .isEqualTo(Score.of(9, 0));
    }

    @DisplayName("pins() 는 pin값을 담은 리스트를 반환한다.")
    @Test
    void pinsTest() {
        assertThat(new Spare(Pin.from(4), Pin.from(6)).pins()).isEqualTo(
                Arrays.asList(Pin.from(4), Pin.from(6))
        );
    }
}
