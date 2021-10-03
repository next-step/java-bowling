package bowling.domain.score;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
public class NormalScoreTest {

    @Test
    public void NormalScore를_생성할_수_있다() {
        //given
        //when
        //then
        assertThat(NormalScore.first(9)).isEqualTo(NormalScore.first(9));
    }

    @Test
    public void NormalScore의_첫번째가_10점이면_스트라이크이다() {
        //given
        //when
        NormalScore score = NormalScore.first(10);
        //then
        assertTrue(score.isStrike());
    }

    @Test
    public void NormalScore가_스트라이크가_아니면_합산이_10점이면_스페어이다() {
        //given
        //when
        //then
        assertAll(
                () -> assertFalse(NormalScore.first(10).accumulate(0).isSpare()),
                () -> assertTrue(NormalScore.first(3).accumulate(7).isSpare())
        );
    }

    @Test
    public void 첫번째_스코어가_0점인지_알_수_있다() {
        //given
        //when
        //then번
        assertTrue(NormalScore.first(0).accumulate(10).isFirstTryNoPoint());
    }

    @Test
    public void 두째_스코어가_0점인지_알_수_있다() {
        //given
        //when
        //then
        assertTrue(NormalScore.first(7).accumulate(0).isSecondTryNoPoint());
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 11, 12, 13})
    public void NormalScore의_첫번째_점수는_0에서_10점까지의_점수만을_가질수_있다(int value) {
        //given
        //when
        //then
        assertThatThrownBy(() -> NormalScore.first(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 점수를 입력하였습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 11, 12, 13})
    public void NormalScore의_두번째_점수는_0에서_10점까지의_점수만을_가질수_있다(int value) {
        //given
        //when
        NormalScore score = NormalScore.first(3);
        //then
        assertThatThrownBy(() -> score.accumulate(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 점수를 입력하였습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    public void NormalScore의_2번째_점수_합산이_10점을_넘으면_익셉션이_발생한다(int value) {
        //given
        //when
        NormalScore score = NormalScore.first(9);
        //then
        assertThatThrownBy(() -> score.accumulate(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1차시도와 2차시도의 합계는 10점을 넘을 수 없습니다.");
    }
}
