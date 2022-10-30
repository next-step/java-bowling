package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecordsTest {

    @Test
    @DisplayName("기록의 개수를 제대로 저장하고 조회할 수 있는지 검증")
    void getRecordCount_record_Test() {
        Records records = new Records();
        assertThat(records.getRecordCount()).isEqualTo(0);

        records.record(RuleConfig.NUMBER_OF_PIN);
        assertThat(records.getRecordCount()).isEqualTo(1);

        records.record(0);
        assertThat(records.getRecordCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("기록중인 프레임의 종료 판단 함수 테스트")
    void isEndLastFrame1() {
        Records records = new Records();
        for (int index = 0; index < RuleConfig.PITCH_COUNT; index++) {
            assertThat(records.isEndLastFrame()).isFalse();
            records.record(0);
        }
        assertThat(records.isEndLastFrame()).isTrue();
    }


    @Test
    @DisplayName("기록된 최대 프레임 개수 내의 프레임이 Frame 클래스인지 확인")
    void FrameClassTest1() {
        Records records = new Records();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME - 1; index++) {
            records.record(RuleConfig.NUMBER_OF_PIN);
        }

        List<Frame> frames = records.getFrames();
        for (Frame frame : frames) {
            assertThat(frame.getClass()).isEqualTo(Frame.class);
        }
    }

    @Test
    @DisplayName("기록된 최대 프레임 개수의 마지막 프레임이 FinalFrame 클래스인지 확인")
    void FrameClassTest2() {
        Records records = new Records();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            records.record(RuleConfig.NUMBER_OF_PIN);
        }

        List<Frame> frames = records.getFrames();
        Frame frame = frames.get(frames.size() - 1);
        assertThat(frame.getClass()).isEqualTo(FinalFrame.class);
    }

}