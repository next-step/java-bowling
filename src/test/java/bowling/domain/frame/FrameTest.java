package bowling.domain.frame;

import bowling.domain.bowl.FirstBowl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

    @DisplayName("프레임 번호가 1 ~ 10 이 아니라면 예외를 던진다.")
    @ParameterizedTest(name = "[{index}] number: {0}")
    @ValueSource(ints = {-1, 0, 11})
    void create_illegalNumber(int number) {
        FirstBowl bowl = new FirstBowl();
        assertThatThrownBy(() -> new Frame(number, bowl))
                .isInstanceOf(IllegalFrameNumberException.class);
    }

}
