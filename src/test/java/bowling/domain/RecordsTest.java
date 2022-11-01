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

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : STRIKE-STRIKE-STRIKE 인 경우")
    void getBonusScoreTest1() {
        Records records = new Records();
        records.record(10);
        records.record(10);
        records.record(10);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.STRIKE);
        FrameScore result3 = records.getFrames().get(2).getResult();
        assertThat(result3).isEqualTo(FrameScore.STRIKE);
        assertThat(records.getBonusScore(0)).isEqualTo(20);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : STRIKE-STRIKE-SPARE 인 경우")
    void getBonusScoreTest2() {
        Records records = new Records();
        records.record(10);
        records.record(10);
        records.record(5);
        records.record(5);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.STRIKE);
        FrameScore result3 = records.getFrames().get(2).getResult();
        assertThat(result3).isEqualTo(FrameScore.SPARE);
        assertThat(records.getBonusScore(0)).isEqualTo(15);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : STRIKE-STRIKE-MISS 인 경우")
    void getBonusScoreTest3() {
        Records records = new Records();
        records.record(10);
        records.record(10);
        records.record(5);
        records.record(4);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.STRIKE);
        FrameScore result3 = records.getFrames().get(2).getResult();
        assertThat(result3).isEqualTo(FrameScore.FINISH);
        assertThat(records.getBonusScore(0)).isEqualTo(15);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : STRIKE-STRIKE-GUTTER 인 경우")
    void getBonusScoreTest4() {
        Records records = new Records();
        records.record(10);
        records.record(10);
        records.record(0);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.STRIKE);
        FrameScore result3 = records.getFrames().get(2).getResult();
        assertThat(result3).isEqualTo(FrameScore.ONGOING);
        assertThat(records.getBonusScore(0)).isEqualTo(10);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : STRIKE-STRIKE-ONGOING 인 경우")
    void getBonusScoreTest5() {
        Records records = new Records();
        records.record(10);
        records.record(10);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.STRIKE);
        assertThat(records.getBonusScore(0)).isEqualTo(10);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : STRIKE-SPARE 인 경우")
    void getBonusScoreTest6() {
        Records records = new Records();
        records.record(10);
        records.record(5);
        records.record(5);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.SPARE);
        assertThat(records.getBonusScore(0)).isEqualTo(10);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : STRIKE-MISS 인 경우")
    void getBonusScoreTest7() {
        Records records = new Records();
        records.record(10);
        records.record(5);
        records.record(4);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.FINISH);
        assertThat(records.getBonusScore(0)).isEqualTo(9);
    }


    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : STRIKE-GUTTER 인 경우")
    void getBonusScoreTest8() {
        Records records = new Records();
        records.record(10);
        records.record(0);
        records.record(0);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.FINISH);
        assertThat(records.getBonusScore(0)).isEqualTo(0);
    }

    @DisplayName("보너스 점수 조회 함수 검증 : STRIKE-ONGOING 인 경우")
    void getBonusScoreTest9() {
        Records records = new Records();
        records.record(10);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        assertThat(records.getBonusScore(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : SPARE-STRIKE 인 경우")
    void getBonusScoreTest10() {
        Records records = new Records();
        records.record(5);
        records.record(5);
        records.record(10);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.SPARE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.STRIKE);
        assertThat(records.getBonusScore(0)).isEqualTo(10);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : SPARE-SPARE 인 경우")
    void getBonusScoreTest11() {
        Records records = new Records();
        records.record(5);
        records.record(5);
        records.record(5);
        records.record(5);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.SPARE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.SPARE);
        assertThat(records.getBonusScore(0)).isEqualTo(5);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : SPARE-MISS 인 경우")
    void getBonusScoreTest12() {
        Records records = new Records();
        records.record(5);
        records.record(5);
        records.record(5);
        records.record(4);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.SPARE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.FINISH);
        assertThat(records.getBonusScore(0)).isEqualTo(5);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : SPARE-GUTTER 인 경우")
    void getBonusScoreTest13() {
        Records records = new Records();
        records.record(5);
        records.record(5);
        records.record(0);

        FrameScore result1 = records.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.SPARE);
        FrameScore result2 = records.getFrames().get(1).getResult();
        assertThat(result2).isEqualTo(FrameScore.ONGOING);
        assertThat(records.getBonusScore(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : SPARE-ONGOING 인 경우")
    void getBonusScoreTest14() {
        Records records = new Records();
        records.record(5);
        records.record(5);

        FrameScore result = records.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.SPARE);
        assertThat(records.getBonusScore(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : MISS 인 경우")
    void getBonusScoreTest15() {
        Records records = new Records();
        records.record(5);
        records.record(4);

        FrameScore result = records.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.FINISH);
        assertThat(records.getBonusScore(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : GUTTER 인 경우")
    void getBonusScoreTest16() {
        Records records = new Records();
        records.record(0);
        records.record(0);

        FrameScore result = records.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.FINISH);
        assertThat(records.getBonusScore(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : ONGOING 인 경우")
    void getBonusScoreTest17() {
        Records records = new Records();
        records.record(5);

        FrameScore result = records.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.ONGOING);
        assertThat(records.getBonusScore(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("보너스 점수 조회 함수 검증 : 시작 전의 경우")
    void getBonusScoreTest18() {
        Records records = new Records();
        assertThat(records.getBonusScore(0)).isEqualTo(0);
    }

}