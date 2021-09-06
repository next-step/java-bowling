package bowling.step2;

import bowling.step2.domain.Pitch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PitchTest {
    @Test
    @DisplayName("10개 보다 작은 갯수를 쓰러뜨리면 성공")
    public void pitchSuccess() {
        //given

        //when
        Pitch pitch = Pitch.of(10);

        //then
        assertThat(pitch.count()).isEqualTo(10);
    }

    @Test
    @DisplayName("10개 보다 많은 갯수를 쓰러뜨리면 실패")
    public void pitchFail() {
        //given

        //when
        assertThatThrownBy(() -> Pitch.of(11)).isInstanceOf(RuntimeException.class);

        //then
    }
}
