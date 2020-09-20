package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.roll.Roll;
import bowling.domain.roll.RollType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class FrameTest {

    @Test
    @DisplayName("다음 프레임 생성(normal)")
    void testNextFrameNormal() {
        // given
        int number = 1;
        Frame frame = new NormalFrame(number);
        // when
        Frame next = frame.nextFrame();
        // then
        assertThat(next).isInstanceOf(NormalFrame.class);
        assertThat(next.isFinalFrame()).isFalse();
        assertThat(next.getNumber()).isEqualTo(number + 1);
    }

    @Test
    @DisplayName("다음 프레임 생성(final)")
    void testNextFrameFinal() {
        // given
        int number = 9;
        Frame frame = new NormalFrame(number);
        // when
        Frame next = frame.nextFrame();
        // then
        assertThat(next).isInstanceOf(FinalFrame.class);
        assertThat(next.isFinalFrame()).isTrue();
        assertThat(next.getNumber()).isEqualTo(number + 1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("프레임 번호 음수이거나 10 초과일 경우 예외")
    void testInvalidFrameNumber(int number) {
        assertThatIllegalArgumentException().isThrownBy(() -> new NormalFrame(number));
    }

    @Test
    @DisplayName("노멀 프레임 번호 1 ~ 9 아닐 경우 예외")
    void testInvalidNormalFrameNumber() {
        assertThatIllegalArgumentException().isThrownBy(() -> new NormalFrame(0));
        assertThatIllegalArgumentException().isThrownBy(() -> new NormalFrame(10));
    }

    @Test
    @DisplayName("마지막 프레임 번호 10 아닐 경우 예외")
    void testInvalidFinalFrameNumber() {
        assertThatIllegalArgumentException().isThrownBy(() -> new FinalFrame(1));
    }

    @Test
    @DisplayName("스트라이크 상태 핀 전달 시 한번만 가능 (normal)")
    void testNormalFrameCannotRoleMore() {
        // given
        Pins pins = new Pins();
        Frame frame = new NormalFrame(1);
        // when
        pins.knockedDown(10);
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(true);
    }

    @Test
    @DisplayName("스트라이크 실패 핀 전달 시 두번 가능 (normal)")
    void testNormalFrameCanRoleMore() {
        // given
        Pins pins = new Pins();
        Frame frame = new NormalFrame(1);
        // when
        pins.knockedDown(2);
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(false);

        // when
        pins.knockedDown(8);
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(true);
    }

    @Test
    @DisplayName("3연속 스트라이크로 2번 보너스 (final)")
    void testAllStrikeFinal() {
        // given
        Pins pins = new Pins();
        Frame frame = new FinalFrame(10);

        // when
        pins.knockedDown(10);
        frame.addRoll(new Roll(RollType.STRIKE, 10));
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(false);

        // when
        pins.reset();
        pins.knockedDown(10);
        frame.addRoll(new Roll(RollType.STRIKE, 10));
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(false);

        // when
        pins.reset();
        pins.knockedDown(10);
        frame.addRoll(new Roll(RollType.STRIKE, 10));
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(true);
    }

    @Test
    @DisplayName("스페어로 1번 보너스 (final)")
    void testSpareFinal() {
        // given
        Pins pins = new Pins();
        Frame frame = new FinalFrame(10);
        // when
        pins.knockedDown(5);
        frame.addRoll(new Roll(RollType.DEFAULT, 5));
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(false);

        // when
        pins.knockedDown(5);
        frame.addRoll(new Roll(RollType.SPARE, 5));
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(false);

        // when
        pins.reset();
        pins.knockedDown(10);
        frame.addRoll(new Roll(RollType.STRIKE, 10));
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(true);
    }

    @Test
    @DisplayName("스페어 실패로 보너스 X (final)")
    void testSpareFailFinal() {
        // given
        Pins pins = new Pins();
        Frame frame = new FinalFrame(10);
        // when
        pins.knockedDown(5);
        frame.addRoll(new Roll(RollType.DEFAULT, 5));
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(false);

        // when
        pins.knockedDown(1);
        frame.addRoll(new Roll(RollType.DEFAULT, 1));
        // then
        assertThat(frame.cannotRollMore(pins)).isEqualTo(true);
    }

    @Test
    @DisplayName("스트라이크 프레임 점수 & 마킹 확인")
    void testStrikeFrame() {
        // given
        Frame frame = new FinalFrame(10);
        // when
        frame.addRoll(new Roll(RollType.STRIKE, 10));
        // then
        assertThat(frame.getMarking()).isEqualTo("X");
    }

    @Test
    @DisplayName("스페어 프레임 점수 & 마킹 확인")
    void testSpareFrame() {
        // given
        Frame frame = new FinalFrame(10);
        // when
        frame.addRoll(new Roll(RollType.DEFAULT, 2));
        frame.addRoll(new Roll(RollType.SPARE, 8));
        // then
        assertThat(frame.getMarking()).isEqualTo("2|/");
    }
}
