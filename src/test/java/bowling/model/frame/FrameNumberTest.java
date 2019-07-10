package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bowling.model.frame.FrameNumber.MAX;
import static bowling.model.frame.FrameNumber.MIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FrameNumberTest {

    @DisplayName("프레임의 번호를 생성하는데 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {MIN, MAX})
    void createFrameNumber_success(int number) {
        // when
        FrameNumber frameNumber = FrameNumber.of(number);

        // then
        assertThat(number).isNotNull();
        assertThat(frameNumber).isEqualTo(FrameNumber.of(number));
    }

    @DisplayName(MIN + "미만 " + MAX + " 초과할 경우 번호 생성에 실패한다")
    @ParameterizedTest
    @ValueSource(ints = {MIN - 1, MAX + 1})
    void createFrameNumber_fail(int number) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> FrameNumber.of(number));
    }
}