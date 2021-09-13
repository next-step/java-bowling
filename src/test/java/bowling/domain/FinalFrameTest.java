package bowling.domain;

import bowling.exception.StrikeFinalFrameInvalidPitchesSumException;
import bowling.exception.NoStrikeFinalFrameInvalidPitchesSumException;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @Test
    @DisplayName("add 테스트 - 1번째 투구가 스트라이크가 아니고 투구의 합이 10이 넘는 경우")
    public void noneStrikeInvalidPitchesSum() {
        // given
        FinalFrame finalFrame = new FinalFrame();
        Pitch firstPitch = new Pitch(5);
        Pitch secondPitch = new Pitch(7);

        // when
        finalFrame.addPitchIfPossible(firstPitch);
        ThrowingCallable throwingCallable = () -> finalFrame.addPitchIfPossible(secondPitch);;

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(NoStrikeFinalFrameInvalidPitchesSumException.class);
    }

    @Test
    @DisplayName("add 테스트 - 1번째 투구가 스트라이크이고 모든 투구의 합이 20이 넘는 경우")
    public void singleStrikeInvalidPitchesSum() {
        // given
        FinalFrame finalFrame = new FinalFrame();
        Pitch firstPitch = new Pitch(10);
        Pitch secondPitch = new Pitch(7);
        Pitch thirdPitch = new Pitch(7);

        // when
        finalFrame.addPitchIfPossible(firstPitch);
        finalFrame.addPitchIfPossible(secondPitch);
        ThrowingCallable throwingCallable = () -> finalFrame.addPitchIfPossible(thirdPitch);;

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(StrikeFinalFrameInvalidPitchesSumException.class);
    }

    @Test
    @DisplayName("add 테스트 - Strike 가 없는 경우")
    public void noStrikes() {
        // given
        FinalFrame finalFrame = new FinalFrame();
        Pitch firstPitch = new Pitch(0);
        Pitch secondPitch = new Pitch(7);
        Pitch thirdPitch = new Pitch(3);

        // when
        boolean firstPitchResult = finalFrame.addPitchIfPossible(firstPitch);
        boolean secondPitchResult = finalFrame.addPitchIfPossible(secondPitch);
        boolean thirdPitchResult = finalFrame.addPitchIfPossible(thirdPitch);

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(secondPitchResult).isEqualTo(true);
        assertThat(thirdPitchResult).isEqualTo(false);
    }

    @Test
    @DisplayName("add 테스트 - 1번째 투구만 Strike 인 경우")
    public void singleStrike() {
        // given
        FinalFrame finalFrame = new FinalFrame();
        Pitch firstPitch = new Pitch(10);
        Pitch secondPitch = new Pitch(7);
        Pitch thirdPitch = new Pitch(3);
        Pitch lastPitch = new Pitch(5);

        // when
        boolean firstPitchResult = finalFrame.addPitchIfPossible(firstPitch);
        boolean secondPitchResult = finalFrame.addPitchIfPossible(secondPitch);
        boolean thirdPitchResult = finalFrame.addPitchIfPossible(thirdPitch);
        boolean lastPitchResult = finalFrame.addPitchIfPossible(lastPitch);

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(secondPitchResult).isEqualTo(true);
        assertThat(thirdPitchResult).isEqualTo(true);
        assertThat(lastPitchResult).isEqualTo(false);
    }

    @Test
    @DisplayName("add 테스트 - 1,2번째 투구 Strike 인 경우")
    public void doubleStrike() {
        // given
        FinalFrame finalFrame = new FinalFrame();
        Pitch firstPitch = new Pitch(10);
        Pitch secondPitch = new Pitch(10);
        Pitch thirdPitch = new Pitch(10);
        Pitch lastPitch = new Pitch(5);

        // when
        boolean firstPitchResult = finalFrame.addPitchIfPossible(firstPitch);
        boolean secondPitchResult = finalFrame.addPitchIfPossible(secondPitch);
        boolean thirdPitchResult = finalFrame.addPitchIfPossible(thirdPitch);
        boolean lastPitchResult = finalFrame.addPitchIfPossible(lastPitch);

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(secondPitchResult).isEqualTo(true);
        assertThat(thirdPitchResult).isEqualTo(true);
        assertThat(lastPitchResult).isEqualTo(false);
    }

    @Test
    @DisplayName("isFull 테스트 - 투구가 남은 경우")
    public void notFull() {
        // given
        FinalFrame finalFrame = new FinalFrame();
        Pitch firstPitch = new Pitch(10);
        Pitch secondPitch = new Pitch(10);

        // when
        boolean firstPitchResult = finalFrame.addPitchIfPossible(firstPitch);
        boolean secondPitchResult = finalFrame.addPitchIfPossible(secondPitch);
        boolean full = finalFrame.isFull();

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(secondPitchResult).isEqualTo(true);
        assertThat(full).isEqualTo(false);
    }

    @Test
    @DisplayName("isFull 테스트 - 모든 투구가 끝난 경우")
    public void isFull() {
        // given
        FinalFrame finalFrame = new FinalFrame();
        Pitch firstPitch = new Pitch(10);
        Pitch secondPitch = new Pitch(10);
        Pitch thirdPitch = new Pitch(10);

        // when
        boolean firstPitchResult = finalFrame.addPitchIfPossible(firstPitch);
        boolean secondPitchResult = finalFrame.addPitchIfPossible(secondPitch);
        boolean thirdPitchResult = finalFrame.addPitchIfPossible(thirdPitch);
        boolean full = finalFrame.isFull();

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(secondPitchResult).isEqualTo(true);
        assertThat(thirdPitchResult).isEqualTo(true);
        assertThat(full).isEqualTo(true);
    }

    @Test
    @DisplayName("score 테스트")
    public void score() {
        // given
        FinalFrame finalFrame = new FinalFrame();
        Pitch firstPitch = new Pitch(10);
        Pitch secondPitch = new Pitch(10);
        Pitch thirdPitch = new Pitch(10);
        int expectedScore = 30;

        // when
        boolean firstPitchResult = finalFrame.addPitchIfPossible(firstPitch);
        boolean secondPitchResult = finalFrame.addPitchIfPossible(secondPitch);
        boolean thirdPitchResult = finalFrame.addPitchIfPossible(thirdPitch);
        int score = finalFrame.score();

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(secondPitchResult).isEqualTo(true);
        assertThat(thirdPitchResult).isEqualTo(true);
        assertThat(score).isEqualTo(expectedScore);
    }

}