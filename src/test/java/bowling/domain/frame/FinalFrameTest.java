package bowling.domain.frame;

import bowling.domain.Score;
import bowling.domain.dto.Record;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static bowling.domain.frame.KindOfFrame.FINAL;
import static bowling.domain.state.BowlingRecordState.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalFrameTest {


    @Test
    @DisplayName("시작")
    void is_started() {
        //given
        FinalFrame finalFrame = new FinalFrame();
        //when
        Record record = finalFrame.getRecord();
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isFalse(),
                () -> assertThat(finalFrame.getRemainPins()).isEqualTo(10),
                () -> assertThat(record.getState()).isEqualTo(STARTED),
                () -> assertThat(record.getKind()).isEqualTo(FINAL),
                () -> assertThat(record.getScores()).isEqualTo(List.of())
        );
    }

    @Test
    @DisplayName("진행중")
    void is_running() {
        //given
        FinalFrame finalFrame = new FinalFrame();
        //when
        finalFrame.bowl(new Score(5));
        Record record = finalFrame.getRecord();
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isFalse(),
                () -> assertThat(finalFrame.getRemainPins()).isEqualTo(5),
                () -> assertThat(record.getState()).isEqualTo(RUNNING),
                () -> assertThat(record.getKind()).isEqualTo(FINAL),
                () -> assertThat(record.getScores()).isEqualTo(List.of(5))
        );
    }

    @Test
    @DisplayName("스트라이크")
    void is_strike() {
        //given
        FinalFrame finalFrame = new FinalFrame();
        //when
        finalFrame.bowl(new Score(10));
        Record record = finalFrame.getRecord();
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isFalse(),
                () -> assertThat(finalFrame.getRemainPins()).isZero(),
                () -> assertThat(record.getState()).isEqualTo(STRIKE),
                () -> assertThat(record.getKind()).isEqualTo(FINAL),
                () -> assertThat(record.getScores()).isEqualTo(List.of(10))
        );
    }

    @Test
    @DisplayName("스페어")
    void is_spare() {
        //given
        FinalFrame finalFrame = new FinalFrame();
        //when
        finalFrame.bowl(new Score(5));
        finalFrame.bowl(new Score(5));
        Record record = finalFrame.getRecord();
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isFalse(),
                () -> assertThat(finalFrame.getRemainPins()).isZero(),
                () -> assertThat(record.getState()).isEqualTo(SPARE),
                () -> assertThat(record.getKind()).isEqualTo(FINAL),
                () -> assertThat(record.getScores()).isEqualTo(List.of(5, 5))
        );
    }

    @Test
    @DisplayName("미스")
    void is_miss() {
        //given
        FinalFrame finalFrame = new FinalFrame();
        //when
        finalFrame.bowl(new Score(5));
        finalFrame.bowl(new Score(4));
        Record record = finalFrame.getRecord();
        //then
        assertAll(
                () -> assertThat(finalFrame.isFinish()).isTrue(),
                () -> assertThat(finalFrame.getRemainPins()).isNotZero(),
                () -> assertThat(record.getState()).isEqualTo(MISS),
                () -> assertThat(record.getKind()).isEqualTo(FINAL),
                () -> assertThat(record.getScores()).isEqualTo(List.of(5, 4))
        );
    }

    @Test
    @DisplayName("상태별 보너스 가능 확인")
    void bonus() {
        //given
        FinalFrame miss = new FinalFrame();
        miss.bowl(new Score(5));
        miss.bowl(new Score(4));

        FinalFrame spare = new FinalFrame();
        spare.bowl(new Score(5));
        spare.bowl(new Score(5));

        FinalFrame strike = new FinalFrame();
        strike.bowl(new Score(10));

        //then
        assertAll(
                () -> assertThatIllegalStateException().isThrownBy(() -> miss.bowl(new Score(5))),
                () -> assertThatNoException().isThrownBy(() -> spare.bowl(new Score(5))),
                () -> assertThatNoException().isThrownBy(() -> strike.bowl(new Score(5)))
        );
    }

    @Test
    @DisplayName("레코드에서 보너스 값 확인")
    void record_bonus() {
        //given
        FinalFrame spare = new FinalFrame();
        spare.bowl(new Score(5));
        spare.bowl(new Score(5));
        spare.bowl(new Score(5));
        Record spareRecord = spare.getRecord();
        //then
        assertThat(spareRecord.getBonus()).isEqualTo(5);
    }

}