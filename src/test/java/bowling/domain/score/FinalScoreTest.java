package bowling.domain.score;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        assertThat(FinalScore.first(9).second(1).third(10))
                .isEqualTo(FinalScore.first(9).second(1).third(10));
    }

    @Test
    public void FinalScore의_첫번째가_10점이면_스트라이크이다() {
        //given
        //when
        FinalScore score = FinalScore.first(10);
        //then
        assertTrue(score.isStrike());
    }

    @Test
    public void 스페어인지_알_수_있다() {
        //given
        //when
        FinalScore score = FinalScore.first(9).second(1);
        //then
        assertTrue(score.isSpare());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 11, 12, 13})
    public void FinalScore의_두번째_점수는_0에서_10점까지의_점수만을_가질수_있다(int value) {
        //given
        //when
        FinalScore score = FinalScore.first(3);
        //then
        assertThatThrownBy(() -> score.second(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 점수를 입력하였습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 11, 12, 13})
    public void FinalScore의_세번째_점수는_0에서_10점까지의_점수만을_가질수_있다(int value) {
        //given
        //when
        FinalScore score = FinalScore.first(3).second(7);
        //then
        assertThatThrownBy(() -> score.third(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 점수를 입력하였습니다.");
    }

}
