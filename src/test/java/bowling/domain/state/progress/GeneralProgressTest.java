package bowling.domain.state.progress;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.Pin;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.GeneralFrame;
import bowling.domain.state.end.second.Normal;
import bowling.domain.state.end.second.Spare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GeneralProgressTest {

    private static final Pin FIRST_HIT_PIN = Pin.of(3);
    private static final Pin SECOND_HIT_SPARE_PIN = Pin.of(7);
    private static final Pin SECOND_HIT_GENERAL_PIN = Pin.of(5);

    private final Frame frame = new GeneralFrame(null);

    private FirstProgress firstProgress;


    @BeforeEach
    void init() {
        firstProgress = new FirstProgress(frame, FIRST_HIT_PIN);
    }

    @Test
    @DisplayName("Spare인경우, true를 반환한다.")
    void isSpareTrueTest() {
        assertThat(
            new GeneralProgress(frame, SECOND_HIT_SPARE_PIN, firstProgress).isSpare()).isTrue();
    }

    @Test
    @DisplayName("Spare가 아니면, false를 반환한다.")
    void isSpareFalseTest() {
        assertThat(
            new GeneralProgress(frame, SECOND_HIT_GENERAL_PIN, firstProgress).isSpare()).isFalse();
    }


    @Test
    @DisplayName("이전에 투구한 갯수가 정상적으로 출력된다.")
    void getBeforeProgressPinCountTest() {
        assertThat(new GeneralProgress(frame, SECOND_HIT_GENERAL_PIN,
            firstProgress).getBeforeProgressPinCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("일반 프레임 기준, Spare가 정상적으로 반환된다.")
    void getUpdateStateSpareTest() {
        Progress progress = new GeneralProgress(frame, SECOND_HIT_SPARE_PIN, firstProgress);
        assertThat(progress.getUpdateState(frame, Pin.of(3))).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("일반 프레임 기준, Spare가 아닌경우, Normal이 반환된다.")
    void getUpdateStateNormalTest() {
        Progress progress = new GeneralProgress(frame, SECOND_HIT_GENERAL_PIN, firstProgress);
        assertThat(progress.getUpdateState(frame, Pin.of(3))).isInstanceOf(Normal.class);
    }

    @Test
    @DisplayName("마지막 프레임 기준, Spare가 없는경우, Normal이 반환된다.")
    void getUpdateStateFinalNormalTest() {
        Frame finalFrame = new FinalFrame();
        Progress progress = new GeneralProgress(finalFrame, SECOND_HIT_GENERAL_PIN, firstProgress);

        assertThat(progress.getUpdateState(finalFrame, Pin.of(3))).isInstanceOf(Normal.class);
    }

    @Test
    @DisplayName("마지막 프레임 기준, Spare가 있는경우, GeneralProgress 가 반환된다.")
    void getUpdateStateFinalSpareTest() {
        Frame finalFrame = new FinalFrame();
        Progress progress = new GeneralProgress(finalFrame, SECOND_HIT_SPARE_PIN, firstProgress);

        assertThat(progress.getUpdateState(finalFrame, Pin.of(3))).isInstanceOf(GeneralProgress.class);
    }
}