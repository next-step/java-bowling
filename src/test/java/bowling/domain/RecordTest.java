package bowling.domain;

import bowling.exception.NotReadyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class RecordTest {

    @Test
    @DisplayName("기록의 개수를 제대로 저장하고 조회할 수 있는지 검증")
    void getRecordCount_record_Test() {
        Record record = new Record();
        assertThat(record.getRecordCount()).isEqualTo(0);

        record.record(RuleConfig.NUMBER_OF_PIN);
        assertThat(record.getRecordCount()).isEqualTo(1);

        record.record(0);
        assertThat(record.getRecordCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("기록중인 프레임의 종료 판단 함수 테스트")
    void isEndLastFrame1() {
        Record record = new Record();
        for (int index = 0; index < RuleConfig.PITCH_COUNT; index++) {
            assertThat(record.isEndLastFrame()).isFalse();
            record.record(0);
        }
        assertThat(record.isEndLastFrame()).isTrue();
    }


    @Test
    @DisplayName("기록된 최대 프레임 개수 내의 프레임이 Frame 클래스인지 확인")
    void FrameClassTest1() {
        Record record = new Record();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME - 1; index++) {
            record.record(RuleConfig.NUMBER_OF_PIN);
        }

        List<Frame> frames = record.getFrames();
        for (Frame frame : frames) {
            assertThat(frame.getClass()).isEqualTo(Frame.class);
        }
    }

    @Test
    @DisplayName("기록된 최대 프레임 개수의 마지막 프레임이 FinalFrame 클래스인지 확인")
    void FrameClassTest2() {
        Record record = new Record();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            record.record(RuleConfig.NUMBER_OF_PIN);
        }

        List<Frame> frames = record.getFrames();
        Frame frame = frames.get(frames.size() - 1);
        assertThat(frame.getClass()).isEqualTo(FinalFrame.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-STRIKE 인 경우")
    void getTotalScoreTest1() {
        Record record = new Record();
        record.record(10);
        record.record(10);
        record.record(10);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();
        FrameScore result3 = record.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 10 + 10)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-SPARE 인 경우")
    void getTotalScoreTest2() {
        Record record = new Record();
        record.record(10);
        record.record(10);
        record.record(5);
        record.record(5);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();
        FrameScore result3 = record.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.SPARE),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-MISS 인 경우")
    void getTotalScoreTest3() {
        Record record = new Record();
        record.record(10);
        record.record(10);
        record.record(5);
        record.record(4);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();
        FrameScore result3 = record.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.FINISH),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-GUTTER 인 경우")
    void getTotalScoreTest4() {
        Record record = new Record();
        record.record(10);
        record.record(10);
        record.record(0);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();
        FrameScore result3 = record.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.ONGOING),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 10 + 0)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-ONGOING 인 경우")
    void getTotalScoreTest5() {
        Record record = new Record();
        record.record(10);
        record.record(10);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThatThrownBy(() -> record.getTotalScore(0)).isInstanceOf(NotReadyException.class)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-SPARE 인 경우")
    void getTotalScoreTest6() {
        Record record = new Record();
        record.record(10);
        record.record(5);
        record.record(5);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.SPARE),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 5 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-MISS 인 경우")
    void getTotalScoreTest7() {
        Record record = new Record();
        record.record(10);
        record.record(5);
        record.record(4);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.FINISH),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 5 + 4)
        );
    }


    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-GUTTER 인 경우")
    void getTotalScoreTest8() {
        Record record = new Record();
        record.record(10);
        record.record(0);
        record.record(0);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.FINISH),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 0 + 0)
        );
    }

    @DisplayName("점수 조회 함수 검증 : STRIKE-ONGOING 인 경우")
    void getTotalScoreTest9() {
        Record record = new Record();
        record.record(10);

        FrameScore result1 = record.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        assertThat(record.getTotalScore(0)).isEqualTo(10);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-STRIKE 인 경우")
    void getTotalScoreTest10() {
        Record record = new Record();
        record.record(5);
        record.record(5);
        record.record(10);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 10)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-SPARE 인 경우")
    void getTotalScoreTest11() {
        Record record = new Record();
        record.record(5);
        record.record(5);
        record.record(5);
        record.record(5);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.SPARE),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-MISS 인 경우")
    void getTotalScoreTest12() {
        Record record = new Record();
        record.record(5);
        record.record(5);
        record.record(5);
        record.record(4);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.FINISH),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-GUTTER 인 경우")
    void getTotalScoreTest13() {
        Record record = new Record();
        record.record(5);
        record.record(5);
        record.record(0);

        FrameScore result1 = record.getFrames().get(0).getResult();
        FrameScore result2 = record.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.ONGOING),
                () -> assertThat(record.getTotalScore(0)).isEqualTo(10 + 0)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-ONGOING 인 경우")
    void getTotalScoreTest14() {
        Record record = new Record();
        record.record(5);
        record.record(5);

        FrameScore result = record.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.SPARE);
        assertThatThrownBy(() -> record.getTotalScore(0)).isInstanceOf(NotReadyException.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : MISS 인 경우")
    void getTotalScoreTest15() {
        Record record = new Record();
        record.record(5);
        record.record(4);

        FrameScore result = record.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.FINISH);
        assertThat(record.getTotalScore(0)).isEqualTo(9);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : GUTTER 인 경우")
    void getTotalScoreTest16() {
        Record record = new Record();
        record.record(0);
        record.record(0);

        FrameScore result = record.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.FINISH);
        assertThat(record.getTotalScore(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : ONGOING 인 경우")
    void getTotalScoreTest17() {
        Record record = new Record();
        record.record(5);

        FrameScore result = record.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.ONGOING);
        assertThatThrownBy(() -> record.getTotalScore(0)).isInstanceOf(NotReadyException.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : 시작 전의 경우")
    void getTotalScoreTest18() {
        Record record = new Record();
        assertThatThrownBy(() -> record.getTotalScore(0)).isInstanceOf(NotReadyException.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Strike")
    void getTotalScoreTest19() {
        Record record = new Record();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            record.record(RuleConfig.NUMBER_OF_PIN);
        }
        record.record(RuleConfig.NUMBER_OF_PIN);
        assertThat(record.getTotalScore()).isEqualTo(290);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Spare")
    void getTotalScoreTest20() {
        Record record = new Record();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            record.record(5);
            record.record(5);
        }
        record.record(5);
        assertThat(record.getTotalScore()).isEqualTo(15 * 10);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Miss")
    void getTotalScoreTest21() {
        Record record = new Record();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            record.record(5);
            record.record(4);
        }
        assertThat(record.getTotalScore()).isEqualTo(9 * 10);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Gutter")
    void getTotalScoreTest22() {
        Record record = new Record();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            record.record(0);
            record.record(0);
        }
        assertThat(record.getTotalScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("일반 샘플 예제 검증")
    void getScoreTest() {
        Record record = new Record();

        record.record(10);

        record.record(8);
        record.record(2);

        record.record(8);
        record.record(1);

        assertAll(
                () -> assertThat(record.getTotalScore(0)).isEqualTo(20),
                () -> assertThat(record.getTotalScore(1)).isEqualTo(38),
                () -> assertThat(record.getTotalScore(2)).isEqualTo(47)
        );
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : GUTTER - 실패")
    void isReadyFrameScoreTest1() {
        Record record = new Record();
        record.record(0);

        assertThat(record.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : GUTTER/MISS - 성공")
    void isReadyFrameScoreTest2() {
        Record record = new Record();
        record.record(0);
        record.record(9);

        assertThat(record.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : SPARE - 실패")
    void isReadyFrameScoreTest3() {
        Record record = new Record();

        record.record(1);
        record.record(9);
        assertThat(record.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : SPARE - 성공 1")
    void isReadyFrameScoreTest4() {
        Record record = new Record();

        record.record(1);
        record.record(9);
        record.record(10);
        assertThat(record.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : SPARE - 성공 2")
    void isReadyFrameScoreTest5() {
        Record record = new Record();

        record.record(1);
        record.record(9);
        record.record(0);
        assertThat(record.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 실패 1")
    void isReadyFrameScoreTest6() {
        Record record = new Record();

        record.record(10);
        record.record(10);
        assertThat(record.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 실패 2")
    void isReadyFrameScoreTest7() {
        Record record = new Record();

        record.record(10);
        record.record(0);
        assertThat(record.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 성공 1")
    void isReadyFrameScoreTest8() {
        Record record = new Record();

        record.record(10);
        record.record(10);
        record.record(10);
        assertThat(record.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 성공 1")
    void isReadyFrameScoreTest9() {
        Record record = new Record();

        record.record(10);
        record.record(10);
        record.record(0);
        assertThat(record.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 성공 2")
    void isReadyFrameScoreTest10() {
        Record record = new Record();

        record.record(10);
        record.record(0);
        record.record(0);
        assertThat(record.isReadyFrameScore(0)).isTrue();
    }

}