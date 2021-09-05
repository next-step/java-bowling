package bowling.step2;

import bowling.step2.domain.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {

    @Nested
    @DisplayName("pitch 메소드는")
    class Describe_pitch {

        @Nested
        @DisplayName("수행한 pitch의 합이 10 이하라면")
        class Context_with_total_under_ten {
            @Test
            @DisplayName("테스트가 성공한다")
            public void pitchSuccess() {
                //given
                NormalFrame frame = NormalFrame.of(1);

                //when
                frame.pitch(5);
                frame.pitch(5);

                //then
                assertThat(frame.current()).isEqualTo(Arrays.asList(5, 5));
            }
        }

        @Nested
        @DisplayName("수행한 pitch의 합이 10 이상이라면")
        class Context_with_total_over_ten {
            @Test
            @DisplayName("테스트가 실패한다")
            public void pitchFail() {
                //given
                NormalFrame frame = NormalFrame.of(1);

                //when
                frame.pitch(5);
                assertThatThrownBy(() -> frame.pitch(6)).isInstanceOf(RuntimeException.class);

                //then
            }
        }
    }
}
