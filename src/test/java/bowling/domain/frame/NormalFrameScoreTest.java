package bowling.domain.frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
class NormalFrameScoreTest {

    @Test
    public void NormalFrameScore를_만들_수_있다() {
        //given
        //when
        //then
        assertThat(NormalFrameScore.of(1, 2)).isEqualTo(NormalFrameScore.of(1, 2));
    }

    @Test
    public void 값이_비어있으면_익셉션이_발생한다() {
        //given
        //when
        //then
        assertAll(
                () -> assertThatThrownBy(() -> NormalFrameScore.of(Collections.emptyList()))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("프레임의 점수가 비어있습니다."),
                () -> assertThatThrownBy(() -> NormalFrameScore.of((List<Integer>) null))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessageContaining("프레임의 점수가 비어있습니다.")
        );

    }

    @Test
    public void 점수의_갯수는_2개가_아니면_익셉션이_발생한다() {
        //given
        //when
        //then
        assertThatThrownBy(() -> NormalFrameScore.of(1, 2, 3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("프레임의 점수는 2개여야 합니다.");
    }

    @ParameterizedTest
    @MethodSource
    public void 각각의_점수는_0에서_10점사이의_값이_아닌경우_익셉션이_발생한다(int first, int second) {
        //given
        //when
        //then
        assertThatThrownBy(() -> NormalFrameScore.of(first, second))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("점수는 0에서 10 사이의 값만을 가져야합니다.");
    }

    private static Stream<Arguments> 각각의_점수는_0에서_10점사이의_값이_아닌경우_익셉션이_발생한다() {
        return Stream.of(
                Arguments.of(11, 0),
                Arguments.of(1, -1),
                Arguments.of(-2, -5),
                Arguments.of(13, 14)
        );
    }

}