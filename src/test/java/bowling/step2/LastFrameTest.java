package bowling.step2;

import bowling.step2.domain.LastFrame;
import bowling.step2.domain.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LastFrameTest {

    @Nested
    @DisplayName("pitch 메소드는")
    class Describe_pitch {

        @Nested
        @DisplayName("현재의 pitch와 이전 pitch의 합이 10 이하일 때")
        class Context_with_total_under_ten {
            @Test
            @DisplayName("테스트가 성공한다")
            public void pitchSuccess() {
                //given
                LastFrame frame = LastFrame.of(10);

                //when
                frame.pitch(5);
                frame.pitch(5);
                frame.pitch(6);

                //then
                assertThat(frame.current()).isEqualTo(Arrays.asList(5, 5, 6));
            }
        }

        @Nested
        @DisplayName("현재의 pitch와 이전 pitch의 합이 10 이상일 때")
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

        @Nested
        @DisplayName("더이상 pitch 할 수 없는 상태에서 pitch 하면")
        class Context_with_additional_pitch {
            @Test
            @DisplayName("테스트가 실패한다")
            public void pitchFail() {
                //given
                LastFrame frame = LastFrame.of(10);

                //when
                frame.pitch(0);
                frame.pitch(0);
                assertThatThrownBy(() -> frame.pitch(6)).isInstanceOf(RuntimeException.class);

                //then
            }
        }
    }
}
