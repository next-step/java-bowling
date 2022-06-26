package bowling.domain.state;

import bowling.exception.InvalidScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @DisplayName("Pins 객체 생성시 10 이상 숫자 입력")
    @Test
    void construct_over10pins() {
        assertThatThrownBy(() -> new Pins(11))
                .isInstanceOf(InvalidScoreException.class)
                .hasMessageContaining("쓰러진 볼링 핀의 수는 최대 10을 넘을 수 없습니다.");
    }


    @DisplayName("Pins 객체 생성시 10 이상 숫자 입력")
    @Test
    void construct_under0pins() {
        assertThatThrownBy(() -> new Pins(-1))
                .isInstanceOf(InvalidScoreException.class)
                .hasMessageContaining("쓰러진 볼링 핀의 수는 최소 0 미만일 수 없습니다.");
    }
}