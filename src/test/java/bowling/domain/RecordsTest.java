package bowling.domain;

import bowling.exception.NotReadyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-STRIKE 인 경우")
    void getTotalScoreTest1() {
        Records records = new Records();
        records.record(10);
        records.record(10);
        records.record(10);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();
        FrameScore result3 = records.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 10 + 10)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-SPARE 인 경우")
    void getTotalScoreTest2() {
        Records records = new Records();
        records.record(10);
        records.record(10);
        records.record(5);
        records.record(5);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();
        FrameScore result3 = records.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.SPARE),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-MISS 인 경우")
    void getTotalScoreTest3() {
        Records records = new Records();
        records.record(10);
        records.record(10);
        records.record(5);
        records.record(4);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();
        FrameScore result3 = records.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.FINISH),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-GUTTER 인 경우")
    void getTotalScoreTest4() {
        Records records = new Records();
        records.record(10);
        records.record(10);
        records.record(0);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();
        FrameScore result3 = records.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.ONGOING),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 10 + 0)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-ONGOING 인 경우")
    void getTotalScoreTest5() {
        Records records = new Records();
        records.record(10);
        records.record(10);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThatThrownBy(() -> records.getTotalScore(0)).isInstanceOf(NotReadyException.class)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-SPARE 인 경우")
    void getTotalScoreTest6() {
        Records records = new Records();
        records.record(10);
        records.record(5);
        records.record(5);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.SPARE),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 5 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-MISS 인 경우")
    void getTotalScoreTest7() {
        Records records = new Records();
        records.record(10);
        records.record(5);
        records.record(4);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.FINISH),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 5 + 4)
        );
    }


    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-GUTTER 인 경우")
    void getTotalScoreTest8() {
        Records records = new Records();
        records.record(10);
        records.record(0);
        records.record(0);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.FINISH),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 0 + 0)
        );
    }

    @DisplayName("점수 조회 함수 검증 : STRIKE-ONGOING 인 경우")
    void getTotalScoreTest9() {
        Records records = new Records();
        records.record(10);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        assertThat(records.getTotalScore(0)).isEqualTo(10);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-STRIKE 인 경우")
    void getTotalScoreTest10() {
        Records records = new Records();
        records.record(5);
        records.record(5);
        records.record(10);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 10)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-SPARE 인 경우")
    void getTotalScoreTest11() {
        Records records = new Records();
        records.record(5);
        records.record(5);
        records.record(5);
        records.record(5);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.SPARE),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-MISS 인 경우")
    void getTotalScoreTest12() {
        Records records = new Records();
        records.record(5);
        records.record(5);
        records.record(5);
        records.record(4);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.FINISH),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-GUTTER 인 경우")
    void getTotalScoreTest13() {
        Records records = new Records();
        records.record(5);
        records.record(5);
        records.record(0);

        FrameScore result1 = records.getFrames().get(0).getResult();
        FrameScore result2 = records.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.ONGOING),
                () -> assertThat(records.getTotalScore(0)).isEqualTo(10 + 0)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-ONGOING 인 경우")
    void getTotalScoreTest14() {
        Records records = new Records();
        records.record(5);
        records.record(5);

        FrameScore result = records.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.SPARE);
        assertThatThrownBy(() -> records.getTotalScore(0)).isInstanceOf(NotReadyException.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : MISS 인 경우")
    void getTotalScoreTest15() {
        Records records = new Records();
        records.record(5);
        records.record(4);

        FrameScore result = records.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.FINISH);
        assertThat(records.getTotalScore(0)).isEqualTo(9);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : GUTTER 인 경우")
    void getTotalScoreTest16() {
        Records records = new Records();
        records.record(0);
        records.record(0);

        FrameScore result = records.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.FINISH);
        assertThat(records.getTotalScore(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : ONGOING 인 경우")
    void getTotalScoreTest17() {
        Records records = new Records();
        records.record(5);

        FrameScore result = records.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.ONGOING);
        assertThatThrownBy(() -> records.getTotalScore(0)).isInstanceOf(NotReadyException.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : 시작 전의 경우")
    void getTotalScoreTest18() {
        Records records = new Records();
        assertThatThrownBy(() -> records.getTotalScore(0)).isInstanceOf(NotReadyException.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Strike")
    void getTotalScoreTest19() {
        Records records = new Records();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            records.record(RuleConfig.NUMBER_OF_PIN);
        }
        records.record(RuleConfig.NUMBER_OF_PIN);
        assertThat(records.getTotalScore()).isEqualTo(290);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Spare")
    void getTotalScoreTest20() {
        Records records = new Records();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            records.record(5);
            records.record(5);
        }
        records.record(5);
        assertThat(records.getTotalScore()).isEqualTo(15 * 10);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Miss")
    void getTotalScoreTest21() {
        Records records = new Records();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            records.record(5);
            records.record(4);
        }
        assertThat(records.getTotalScore()).isEqualTo(9 * 10);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Gutter")
    void getTotalScoreTest22() {
        Records records = new Records();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            records.record(0);
            records.record(0);
        }
        assertThat(records.getTotalScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("일반 샘플 예제 검증")
    void getScoreTest() {
        Records records = new Records();

        records.record(10);

        records.record(8);
        records.record(2);

        records.record(8);
        records.record(1);

        assertAll(
                () -> assertThat(records.getTotalScore(0)).isEqualTo(20),
                () -> assertThat(records.getTotalScore(1)).isEqualTo(38),
                () -> assertThat(records.getTotalScore(2)).isEqualTo(47)
        );
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : GUTTER - 실패")
    void isReadyFrameScoreTest1() {
        Records records = new Records();
        records.record(0);

        assertThat(records.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : GUTTER/MISS - 성공")
    void isReadyFrameScoreTest2() {
        Records records = new Records();
        records.record(0);
        records.record(9);

        assertThat(records.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : SPARE - 실패")
    void isReadyFrameScoreTest3() {
        Records records = new Records();

        records.record(1);
        records.record(9);
        assertThat(records.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : SPARE - 성공 1")
    void isReadyFrameScoreTest4() {
        Records records = new Records();

        records.record(1);
        records.record(9);
        records.record(10);
        assertThat(records.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : SPARE - 성공 2")
    void isReadyFrameScoreTest5() {
        Records records = new Records();

        records.record(1);
        records.record(9);
        records.record(0);
        assertThat(records.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 실패 1")
    void isReadyFrameScoreTest6() {
        Records records = new Records();

        records.record(10);
        records.record(10);
        assertThat(records.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 실패 2")
    void isReadyFrameScoreTest7() {
        Records records = new Records();

        records.record(10);
        records.record(0);
        assertThat(records.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 성공 1")
    void isReadyFrameScoreTest8() {
        Records records = new Records();

        records.record(10);
        records.record(10);
        records.record(10);
        assertThat(records.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 성공 1")
    void isReadyFrameScoreTest9() {
        Records records = new Records();

        records.record(10);
        records.record(10);
        records.record(0);
        assertThat(records.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 성공 2")
    void isReadyFrameScoreTest10() {
        Records records = new Records();

        records.record(10);
        records.record(0);
        records.record(0);
        assertThat(records.isReadyFrameScore(0)).isTrue();
    }

}