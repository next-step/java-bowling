package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("NonAsciiCharacters")
public class FinalScoreTest {

    @Test
    public void FinalScore를_만들_수_있다() {
        //given
        //when
        //then
        assertThat(FinalScore.first(9).withSecond(1).withThird(10))
                .isEqualTo(FinalScore.first(9).withSecond(1).withThird(10));
    }

    @Test
    public void FinalScore의_첫번째가_10점이면_스트라이크이다() {
        //given
        //when
        FinalScore score = FinalScore.first(10);
        //then
        assertTrue(score.isStrike());
    }

    @ParameterizedTest
    @MethodSource
    public void FinalScore의_첫번째시도와_두번째시도가_스페어_혹은_스트라이크인지_판단할_수_있다(int first, int second) {
        //given
        //when
        FinalScore score = FinalScore.first(first).withSecond(second);
        //then
        assertTrue(score.isSpareOrStrike());
    }

    private static Stream<Arguments> FinalScore의_첫번째시도와_두번째시도가_스페어_혹은_스트라이크인지_판단할_수_있다() {
        return Stream.of(
                Arguments.of(10, 10),
                Arguments.of(1, 9),
                Arguments.of(3, 7),
                Arguments.of(0, 10)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 11, 12, 13})
    public void FinalScore의_두번째_점수는_0에서_10점까지의_점수만을_가질수_있다(int value) {
        //given
        //when
        FinalScore score = FinalScore.first(3);
        //then
        assertThatThrownBy(() -> score.withSecond(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 점수를 입력하였습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 11, 12, 13})
    public void FinalScore의_세번째_점수는_0에서_10점까지의_점수만을_가질수_있다(int value) {
        //given
        //when
        FinalScore score = FinalScore.first(3).withSecond(7);
        //then
        assertThatThrownBy(() -> score.withThird(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 점수를 입력하였습니다.");
    }

}
