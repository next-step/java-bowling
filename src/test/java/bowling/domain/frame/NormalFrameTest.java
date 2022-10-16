package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.dto.Record;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static bowling.domain.frame.KindOfFrame.*;
import static bowling.domain.state.BowlingRecordState.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    @Test
    @DisplayName("첫 시도에 스트라이크면 프레임 종료")
    void is_finish_when_strike() {
        //given
        NormalFrame normalFrame = new NormalFrame();
        //when
        normalFrame.bowl(new Score(10));
        Record record = normalFrame.getRecord();
        //then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isTrue(),
                () -> assertThat(normalFrame.getRemainPins()).isZero(),
                () -> assertThat(record.getState()).isEqualTo(STRIKE),
                () -> assertThat(record.getKind()).isEqualTo(NORMAL),
                () -> assertThat(record.getScores()).isEqualTo(List.of(10))
        );
    }

    @Test
    @DisplayName("첫 시도에 스트라이크가 아니면 프레임 종료 아님 && 남은 핀 개수")
    void is_not_finish_and_remain_pins() {
        //given
        NormalFrame normalFrame = new NormalFrame();
        //when
        normalFrame.bowl(new Score(5));
        Record record = normalFrame.getRecord();
        //then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isFalse(),
                () -> assertThat(normalFrame.getRemainPins()).isEqualTo(5),
                () -> assertThat(record.getState()).isEqualTo(RUNNING),
                () -> assertThat(record.getScores()).isEqualTo(List.of(5)),
                () -> assertThat(record.getKind()).isEqualTo(NORMAL)
        );
    }

    @Test
    @DisplayName("스페어 처리하면 프레임 종료")
    void is_finish_when_spare() {
        //given
        NormalFrame normalFrame = new NormalFrame();
        //when
        normalFrame.bowl(new Score(5));
        normalFrame.bowl(new Score(5));
        Record record = normalFrame.getRecord();
        //then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isTrue(),
                () -> assertThat(normalFrame.getRemainPins()).isZero(),
                () -> assertThat(record.getState()).isEqualTo(SPARE),
                () -> assertThat(record.getScores()).isEqualTo(List.of(5,5)),
                () -> assertThat(record.getKind()).isEqualTo(NORMAL)
        );
    }

    @Test
    @DisplayName("두번 던지면 프레임 종료")
    void is_finish_when_miss() {
        //given
        NormalFrame normalFrame = new NormalFrame();
        //when
        normalFrame.bowl(new Score(5));
        normalFrame.bowl(new Score(2));
        Record record = normalFrame.getRecord();
        //then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isTrue(),
                () -> assertThat(normalFrame.getRemainPins()).isEqualTo(3),
                () -> assertThat(record.getState()).isEqualTo(MISS),
                () -> assertThat(record.getScores()).isEqualTo(List.of(5,2)),
                () -> assertThat(record.getKind()).isEqualTo(NORMAL)
        );
    }

}