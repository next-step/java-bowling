package bowling.domain;

import bowling.exception.NotReadyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class FramesTest {

    @Test
    @DisplayName("기록의 개수를 제대로 저장하고 조회할 수 있는지 검증")
    void getRecordCount_record_Test() {
        Frames frames = new Frames();
        assertThat(frames.getRecordCount()).isEqualTo(0);

        frames.record(RuleConfig.NUMBER_OF_PIN);
        assertThat(frames.getRecordCount()).isEqualTo(1);

        frames.record(0);
        assertThat(frames.getRecordCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("기록중인 프레임의 종료 판단 함수 테스트")
    void isEndLastFrame1() {
        Frames frames = new Frames();
        for (int index = 0; index < RuleConfig.PITCH_COUNT; index++) {
            assertThat(frames.isEndLastFrame()).isFalse();
            frames.record(0);
        }
        assertThat(frames.isEndLastFrame()).isTrue();
    }


    @Test
    @DisplayName("기록된 최대 프레임 개수 내의 프레임이 Frame 클래스인지 확인")
    void FrameClassTest1() {
        Frames frames = new Frames();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME - 1; index++) {
            frames.record(RuleConfig.NUMBER_OF_PIN);
        }

        List<Frame> newFrames = frames.getFrames();
        for (Frame frame : newFrames) {
            assertThat(frame.getClass()).isEqualTo(Frame.class);
        }
    }

    @Test
    @DisplayName("기록된 최대 프레임 개수의 마지막 프레임이 FinalFrame 클래스인지 확인")
    void FrameClassTest2() {
        Frames frames = new Frames();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            frames.record(RuleConfig.NUMBER_OF_PIN);
        }

        List<Frame> newFrames = frames.getFrames();
        Frame frame = newFrames.get(newFrames.size() - 1);
        assertThat(frame.getClass()).isEqualTo(FinalFrame.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-STRIKE 인 경우")
    void getTotalScoreTest1() {
        Frames frames = new Frames();
        frames.record(10);
        frames.record(10);
        frames.record(10);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();
        FrameScore result3 = frames.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 10 + 10)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-SPARE 인 경우")
    void getTotalScoreTest2() {
        Frames frames = new Frames();
        frames.record(10);
        frames.record(10);
        frames.record(5);
        frames.record(5);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();
        FrameScore result3 = frames.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.SPARE),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-MISS 인 경우")
    void getTotalScoreTest3() {
        Frames frames = new Frames();
        frames.record(10);
        frames.record(10);
        frames.record(5);
        frames.record(4);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();
        FrameScore result3 = frames.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.FINISH),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-GUTTER 인 경우")
    void getTotalScoreTest4() {
        Frames frames = new Frames();
        frames.record(10);
        frames.record(10);
        frames.record(0);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();
        FrameScore result3 = frames.getFrames().get(2).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result3).isEqualTo(FrameScore.ONGOING),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 10 + 0)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-STRIKE-ONGOING 인 경우")
    void getTotalScoreTest5() {
        Frames frames = new Frames();
        frames.record(10);
        frames.record(10);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThatThrownBy(() -> frames.getTotalScore(0)).isInstanceOf(NotReadyException.class)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-SPARE 인 경우")
    void getTotalScoreTest6() {
        Frames frames = new Frames();
        frames.record(10);
        frames.record(5);
        frames.record(5);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.SPARE),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 5 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-MISS 인 경우")
    void getTotalScoreTest7() {
        Frames frames = new Frames();
        frames.record(10);
        frames.record(5);
        frames.record(4);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.FINISH),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 5 + 4)
        );
    }


    @Test
    @DisplayName("점수 조회 함수 검증 : STRIKE-GUTTER 인 경우")
    void getTotalScoreTest8() {
        Frames frames = new Frames();
        frames.record(10);
        frames.record(0);
        frames.record(0);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(result2).isEqualTo(FrameScore.FINISH),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 0 + 0)
        );
    }

    @DisplayName("점수 조회 함수 검증 : STRIKE-ONGOING 인 경우")
    void getTotalScoreTest9() {
        Frames frames = new Frames();
        frames.record(10);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        assertThat(result1).isEqualTo(FrameScore.STRIKE);
        assertThat(frames.getTotalScore(0)).isEqualTo(10);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-STRIKE 인 경우")
    void getTotalScoreTest10() {
        Frames frames = new Frames();
        frames.record(5);
        frames.record(5);
        frames.record(10);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.STRIKE),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 10)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-SPARE 인 경우")
    void getTotalScoreTest11() {
        Frames frames = new Frames();
        frames.record(5);
        frames.record(5);
        frames.record(5);
        frames.record(5);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.SPARE),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-MISS 인 경우")
    void getTotalScoreTest12() {
        Frames frames = new Frames();
        frames.record(5);
        frames.record(5);
        frames.record(5);
        frames.record(4);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.FINISH),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 5)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-GUTTER 인 경우")
    void getTotalScoreTest13() {
        Frames frames = new Frames();
        frames.record(5);
        frames.record(5);
        frames.record(0);

        FrameScore result1 = frames.getFrames().get(0).getResult();
        FrameScore result2 = frames.getFrames().get(1).getResult();

        assertAll(
                () -> assertThat(result1).isEqualTo(FrameScore.SPARE),
                () -> assertThat(result2).isEqualTo(FrameScore.ONGOING),
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(10 + 0)
        );
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : SPARE-ONGOING 인 경우")
    void getTotalScoreTest14() {
        Frames frames = new Frames();
        frames.record(5);
        frames.record(5);

        FrameScore result = frames.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.SPARE);
        assertThatThrownBy(() -> frames.getTotalScore(0)).isInstanceOf(NotReadyException.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : MISS 인 경우")
    void getTotalScoreTest15() {
        Frames frames = new Frames();
        frames.record(5);
        frames.record(4);

        FrameScore result = frames.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.FINISH);
        assertThat(frames.getTotalScore(0)).isEqualTo(9);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : GUTTER 인 경우")
    void getTotalScoreTest16() {
        Frames frames = new Frames();
        frames.record(0);
        frames.record(0);

        FrameScore result = frames.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.FINISH);
        assertThat(frames.getTotalScore(0)).isEqualTo(0);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : ONGOING 인 경우")
    void getTotalScoreTest17() {
        Frames frames = new Frames();
        frames.record(5);

        FrameScore result = frames.getFrames().get(0).getResult();
        assertThat(result).isEqualTo(FrameScore.ONGOING);
        assertThatThrownBy(() -> frames.getTotalScore(0)).isInstanceOf(NotReadyException.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : 시작 전의 경우")
    void getTotalScoreTest18() {
        Frames frames = new Frames();
        assertThatThrownBy(() -> frames.getTotalScore(0)).isInstanceOf(NotReadyException.class);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Strike")
    void getTotalScoreTest19() {
        Frames frames = new Frames();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            frames.record(RuleConfig.NUMBER_OF_PIN);
        }
        frames.record(RuleConfig.NUMBER_OF_PIN);
        assertThat(frames.getTotalScore()).isEqualTo(290);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Spare")
    void getTotalScoreTest20() {
        Frames frames = new Frames();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            frames.record(5);
            frames.record(5);
        }
        frames.record(5);
        assertThat(frames.getTotalScore()).isEqualTo(15 * 10);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Miss")
    void getTotalScoreTest21() {
        Frames frames = new Frames();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            frames.record(5);
            frames.record(4);
        }
        assertThat(frames.getTotalScore()).isEqualTo(9 * 10);
    }

    @Test
    @DisplayName("점수 조회 함수 검증 : All Gutter")
    void getTotalScoreTest22() {
        Frames frames = new Frames();
        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            frames.record(0);
            frames.record(0);
        }
        assertThat(frames.getTotalScore()).isEqualTo(0);
    }

    @Test
    @DisplayName("일반 샘플 예제 검증")
    void getScoreTest() {
        Frames frames = new Frames();

        frames.record(10);

        frames.record(8);
        frames.record(2);

        frames.record(8);
        frames.record(1);

        assertAll(
                () -> assertThat(frames.getTotalScore(0)).isEqualTo(20),
                () -> assertThat(frames.getTotalScore(1)).isEqualTo(38),
                () -> assertThat(frames.getTotalScore(2)).isEqualTo(47)
        );
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : GUTTER - 실패")
    void isReadyFrameScoreTest1() {
        Frames frames = new Frames();
        frames.record(0);

        assertThat(frames.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : GUTTER/MISS - 성공")
    void isReadyFrameScoreTest2() {
        Frames frames = new Frames();
        frames.record(0);
        frames.record(9);

        assertThat(frames.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : SPARE - 실패")
    void isReadyFrameScoreTest3() {
        Frames frames = new Frames();

        frames.record(1);
        frames.record(9);
        assertThat(frames.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : SPARE - 성공 1")
    void isReadyFrameScoreTest4() {
        Frames frames = new Frames();

        frames.record(1);
        frames.record(9);
        frames.record(10);
        assertThat(frames.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : SPARE - 성공 2")
    void isReadyFrameScoreTest5() {
        Frames frames = new Frames();

        frames.record(1);
        frames.record(9);
        frames.record(0);
        assertThat(frames.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 실패 1")
    void isReadyFrameScoreTest6() {
        Frames frames = new Frames();

        frames.record(10);
        frames.record(10);
        assertThat(frames.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 실패 2")
    void isReadyFrameScoreTest7() {
        Frames frames = new Frames();

        frames.record(10);
        frames.record(0);
        assertThat(frames.isReadyFrameScore(0)).isFalse();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 성공 1")
    void isReadyFrameScoreTest8() {
        Frames frames = new Frames();

        frames.record(10);
        frames.record(10);
        frames.record(10);
        assertThat(frames.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 성공 1")
    void isReadyFrameScoreTest9() {
        Frames frames = new Frames();

        frames.record(10);
        frames.record(10);
        frames.record(0);
        assertThat(frames.isReadyFrameScore(0)).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 준비 여부 검증 함수 검증 : STRIKE - 성공 2")
    void isReadyFrameScoreTest10() {
        Frames frames = new Frames();

        frames.record(10);
        frames.record(0);
        frames.record(0);
        assertThat(frames.isReadyFrameScore(0)).isTrue();
    }

    @Test
    void isEndFrameTest1() {
        Frames frames = new Frames();
        assertThat(frames.isEndFrame(0)).isFalse();
    }

    @Test
    void isEndFrameTest2() {
        Frames frames = new Frames();
        frames.record(0);
        assertThat(frames.isEndFrame(0)).isFalse();
    }

    @Test
    void isEndFrameTest3() {
        Frames frames = new Frames();
        frames.record(0);
        frames.record(0);
        assertThat(frames.isEndFrame(0)).isTrue();
    }

}