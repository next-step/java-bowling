package bowling.step2;

import bowling.step2.domain.PitchGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PitchGroupTest {

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
                PitchGroup pitchGroup = PitchGroup.of();

                //when
                pitchGroup.pitch(10);

                //then
                assertThat(pitchGroup.pitches()).isEqualTo(Collections.singletonList(10));
            }


            @Test
            @DisplayName("테스트가 성공한다")
            public void pitchSuccess2() {
                //given
                PitchGroup pitchGroup = PitchGroup.of();

                //when
                pitchGroup.pitch(3);
                pitchGroup.pitch(6);

                //then
                assertThat(pitchGroup.pitches()).isEqualTo(Arrays.asList(3, 6));
            }

            @Test
            @DisplayName("수행한 pitch의 수만큼 pitchGroup size를 갖는다")
            public void pitchSize() {
                //given
                PitchGroup pitchGroup = PitchGroup.of();

                //when
                pitchGroup.pitch(3);
                pitchGroup.pitch(3);

                //then
                assertThat(pitchGroup.size()).isEqualTo(2);
            }
        }

        @Nested
        @DisplayName("수행한 pitch의 합이 10 이상이라면")
        class Context_with_total_over_ten {
            @Test
            @DisplayName("테스트가 실패한다")
            public void pitchFail() {
                //given
                PitchGroup pitchGroup = PitchGroup.of();

                //when
                assertThatThrownBy(() -> pitchGroup.pitch(11)).isInstanceOf(RuntimeException.class);

                //then
            }
        }
    }
}
