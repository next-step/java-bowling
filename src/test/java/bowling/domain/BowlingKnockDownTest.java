package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingKnockDownTest {

    @ParameterizedTest
    @DisplayName("쓰러뜨린 볼링핀 수 확인")
    @ValueSource(ints = {10, 9, 7, 0})
    public void 볼링핀_쓰러뜨린수(int countOfKnockDown) {
        BowlingKnockDown bowlingKnockDown = new BowlingKnockDown(countOfKnockDown);
        assertEquals(bowlingKnockDown.getCount(), countOfKnockDown);
    }

    @ParameterizedTest
    @DisplayName("쓰러뜨린 볼링 투구 값 10이상인 경우 예외")
    @ValueSource(ints = {11, 15, 21})
    public void 볼링핀_경계값_테스트(int countOfKnockDown) {
        assertThatThrownBy(() ->  new BowlingKnockDown(countOfKnockDown))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("볼링 핀은 최대 10개입니다.");
    }
}
