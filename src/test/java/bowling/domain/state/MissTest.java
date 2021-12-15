package bowling.domain.state;

import bowling.domain.frame.Pin;
import bowling.domain.frame.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MissTest {

    @DisplayName("bowl() 메서드는 UnsupportOpeationException 반환한다.")
    @Test
    void bowlFailTest() {
        Miss miss = new Miss(Pin.from(1), Pin.from(2));
        assertThatThrownBy(() ->  miss.bowl(Pin.TEN)).isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        Miss miss = new Miss(Pin.from(1), Pin.from(2));
        assertThat(miss).isEqualTo(new Miss(Pin.from(1), Pin.from(2)));
    }

    @DisplayName("쓰러트린 갯수가 10개이면 illegal Exception")
    @Test
    void createFailTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Miss(Pin.from(5), Pin.from(5)));
    }

    @DisplayName("viewString()은 첫번쨰핀|두번째핀 반환한다.")
    @Test
    void viewStringTest() {
        assertThat(new Miss(Pin.from(5), Pin.from(3)).viewString()).isEqualTo("5|3");
    }

    @DisplayName("score()은 Pin의 갯수의 합과 시도횟수 0번을 반환한다..")
    @Test
    void scoreTest() {
        assertThat(new Miss(Pin.from(5), Pin.from(3)).score()).isEqualTo(Score.of(8, 0));
    }

    @DisplayName("calculateAdditionalScore() left에 따라 핀을 더해서 스코어를 반환한다.")
    @Test
    void calculateAdditionalScoreTest() {
        assertThat(new Miss(Pin.from(5), Pin.from(3)).calculateAdditionalScore(Score.of(5, 1)))
                .isEqualTo(Score.of(10, 0));

        assertThat(new Miss(Pin.from(5), Pin.from(3)).calculateAdditionalScore(Score.of(5, 2)))
                .isEqualTo(Score.of(13, 0));
    }
}
