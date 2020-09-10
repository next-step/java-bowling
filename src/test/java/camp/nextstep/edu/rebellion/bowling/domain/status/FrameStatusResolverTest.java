package camp.nextstep.edu.rebellion.bowling.domain.status;

import camp.nextstep.edu.rebellion.bowling.domain.frame.Frame;
import camp.nextstep.edu.rebellion.bowling.domain.frame.FrameFactory;
import camp.nextstep.edu.rebellion.bowling.domain.frame.FrameType;
import camp.nextstep.edu.rebellion.bowling.util.StringUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameStatusResolverTest {
    @DisplayName("아직 투구 전인 프레임일 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void notSTartedTest() {
        // given
        Frame frame = FrameFactory.get(FrameType.NORMAL);

        // when
        FrameStatus status = FrameStatusResolver.resolve(frame);

        // then
        assertAll(
                () -> assertThat(status instanceof NotStarted).isTrue(),
                () -> assertThat(frame.getStatus() instanceof NotStarted).isTrue(),
                () -> assertThat(status.makeSymbol()).isEqualTo(StringUtil.EMPTY)
        );
    }

    @DisplayName("투구 중인 프레임일 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void playingTest() {
        // given
        Frame frame = FrameFactory.get(FrameType.NORMAL);
        frame.markScore(5);

        // when
        FrameStatus status = FrameStatusResolver.resolve(frame);

        // then
        assertAll(
                () -> assertThat(status instanceof Playing).isTrue(),
                () -> assertThat(frame.getStatus() instanceof Playing).isTrue(),
                () -> assertThat(status.makeSymbol()).isEqualTo("5")
        );
    }

    @DisplayName("Strike 인 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void strikeTest() {
        // given
        Frame frame = FrameFactory.get(FrameType.NORMAL);
        frame.markScore(10);

        // when
        FrameStatus status = FrameStatusResolver.resolve(frame);

        // then
        assertAll(
                () -> assertThat(status instanceof Strike).isTrue(),
                () -> assertThat(frame.getStatus() instanceof Strike).isTrue(),
                () -> assertThat(status.makeSymbol()).isEqualTo("X")
        );
    }

    @DisplayName("Spare 인 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void spareTest() {
        // given
        Frame frame = FrameFactory.get(FrameType.NORMAL);
        frame.markScore(9);
        frame.markScore(1);

        // when
        FrameStatus status = FrameStatusResolver.resolve(frame);

        // then
        assertAll(
                () -> assertThat(status instanceof Spare).isTrue(),
                () -> assertThat(frame.getStatus() instanceof Spare).isTrue(),
                () -> assertThat(status.makeSymbol()).isEqualTo("9|/")
        );
    }

    @DisplayName("Miss 인 경우 상태가 바르게 반환되는지 확인")
    @Test
    public void missTest() {
        // given
        Frame frame = FrameFactory.get(FrameType.NORMAL);
        frame.markScore(1);
        frame.markScore(2);

        // when
        FrameStatus status = FrameStatusResolver.resolve(frame);

        // then
        assertAll(
                () -> assertThat(status instanceof Miss).isTrue(),
                () -> assertThat(frame.getStatus() instanceof Miss).isTrue(),
                () -> assertThat(status.makeSymbol()).isEqualTo("1|2")
        );
    }

    @DisplayName("Miss 인 경우 상태가 바르게 반환되는지 확인 (Gutter)")
    @Test
    public void missGutterTest() {
        // given
        Frame frame = FrameFactory.get(FrameType.NORMAL);
        frame.markScore(7);
        frame.markScore(0);

        // when
        FrameStatus status = FrameStatusResolver.resolve(frame);

        // then
        assertAll(
                () -> assertThat(status instanceof Miss).isTrue(),
                () -> assertThat(frame.getStatus() instanceof Miss).isTrue(),
                () -> assertThat(status.makeSymbol()).isEqualTo("7|-")
        );
    }

    @DisplayName("보너스 프레임인 경우 상태가 바르게 반환되는지 확인 (BonusStatus)")
    @Test
    public void bonusStatusTest() {
        // given
        Frame frame = FrameFactory.get(FrameType.BONUS);
        frame.markScore(10);
        frame.markScore(10);

        // when
        FrameStatus status = FrameStatusResolver.resolve(frame);

        // then
        assertAll(
                () -> assertThat(status instanceof BonusStatus).isTrue(),
                () -> assertThat(frame.getStatus() instanceof BonusStatus).isTrue(),
                () -> assertThat(status.makeSymbol()).isEqualTo("X|X")
        );
    }
}
