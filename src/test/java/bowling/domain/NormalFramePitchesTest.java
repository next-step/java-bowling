package bowling.domain;

import bowling.exception.NormalFrameInvalidPitchesSumException;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFramePitchesTest {

    @Test
    @DisplayName("add 테스트 - 투구의 합이 10이 넘는 경우")
    public void invalidPitchesSum() {
        // given
        NormalFramePitches normalFramePitches = new NormalFramePitches();
        Pitch firstPitch = new Pitch(5);
        Pitch secondPitch = new Pitch(7);

        // when
        normalFramePitches.add(firstPitch);
        ThrowingCallable throwingCallable = () -> normalFramePitches.add(secondPitch);;

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(NormalFrameInvalidPitchesSumException.class);
    }

    @Test
    @DisplayName("add 테스트 - Strike 인 경우 투구값 추가하면 false 리턴")
    public void addAfterStrike() {
        // given
        NormalFramePitches normalFramePitches = new NormalFramePitches();
        Pitch firstPitch = new Pitch(10);
        Pitch secondPitch = new Pitch(7);

        // when
        boolean firstPitchResult = normalFramePitches.add(firstPitch);
        boolean secondPitchResult = normalFramePitches.add(secondPitch);

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(secondPitchResult).isEqualTo(false);
    }

    @Test
    @DisplayName("add 테스트 - 2번 투구값 추가한뒤 투구값 추가하면 false 리턴")
    public void addAfterTwoPitches() {
        // given
        NormalFramePitches normalFramePitches = new NormalFramePitches();
        Pitch firstPitch = new Pitch(9);
        Pitch secondPitch = new Pitch(1);
        Pitch thirdPitch = new Pitch(7);

        // when
        boolean firstPitchResult = normalFramePitches.add(firstPitch);
        boolean secondPitchResult = normalFramePitches.add(secondPitch);
        boolean thirdPitchResult = normalFramePitches.add(secondPitch);

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(secondPitchResult).isEqualTo(true);
        assertThat(thirdPitchResult).isEqualTo(false);
    }

    @Test
    @DisplayName("isFull 테스트 - 투구가 남은 경우")
    public void notFull() {
        // given
        NormalFramePitches normalFramePitches = new NormalFramePitches();
        Pitch firstPitch = new Pitch(9);

        // when
        boolean firstPitchResult = normalFramePitches.add(firstPitch);
        boolean full = normalFramePitches.isFull();

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(full).isEqualTo(false);
    }

    @Test
    @DisplayName("isFull 테스트 - 모든 투구가 끝난 경우")
    public void isFull() {
        // given
        NormalFramePitches normalFramePitches = new NormalFramePitches();
        Pitch firstPitch = new Pitch(10);

        // when
        boolean firstPitchResult = normalFramePitches.add(firstPitch);
        boolean full = normalFramePitches.isFull();

        // then
        assertThat(firstPitchResult).isEqualTo(true);
        assertThat(full).isEqualTo(true);
    }

    @Test
    @DisplayName("score 테스트")
    public void score() {
        // given
        NormalFramePitches normalFramePitches = new NormalFramePitches();
        normalFramePitches.add(new Pitch(5));
        normalFramePitches.add(new Pitch(3));
        int expectedScore = 8;

        // when
        int score = normalFramePitches.score(new NormalFrame());

        // then
        assertThat(score).isEqualTo(expectedScore);
    }

}