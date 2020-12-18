package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created By mand2 on 2020-12-18.
 */
class PitchTest {

    @ParameterizedTest
    @DisplayName("투구 생성자")
    @ValueSource(ints = {0, 5, 7, 10})
    void createPitch(int inputScore) {
        Pitch pitch = Pitch.from(inputScore);

        assertThat(pitch).isEqualTo(Pitch.from(inputScore));
    }

    @ParameterizedTest
    @DisplayName("쓰러뜨린 핀의 개수가 0~10 사이를 벗어나면 예외처리")
    @ValueSource(ints = {-1, 11})
    void createPitchExceedRange(int inputScore) {

        assertThatThrownBy(() -> Pitch.from(inputScore))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Pitch.MESSAGE_SCORE_RANGE);
    }

}
