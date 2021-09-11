package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

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
    @ValueSource(ints = {-1, -2, 11, 12, 13})
    public void FinalScore의_첫번째_점수는_0에서_10점까지의_점수만을_가질수_있다(int value) {
        //given
        //when
        //then
        assertThatThrownBy(() -> FinalScore.first(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 점수를 입력하였습니다.");
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

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    public void FinalScore의_2번째_점수_합산이_10점을_넘으면_익셉션이_발생한다(int value) {
        //given
        //when
        FinalScore score = FinalScore.first(9);
        //then
        assertThatThrownBy(() -> score.withSecond(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
    }
}
