package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalFrameTest {
    @Test
    void 다음프레임과_현재_상태를_가지는_프레임을_생성한다() {
        //given
        NormalFrame normalFrame = NormalFrame.of();

        //when
        //then
        assertThat(normalFrame).isNotNull();
    }
}
