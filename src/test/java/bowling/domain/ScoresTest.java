package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoresTest {

    @Test
    @DisplayName("점수 기록 후 기록한 점수의 개수가 제대로 늘어나는지 검증")
    void recordTest1() {
        Scores scores = new Scores();
        assertThat(scores.getDownPins()).hasSize(0);
        scores.record(5);
        assertThat(scores.getDownPins()).hasSize(1);
        scores.record(5);
        assertThat(scores.getDownPins()).hasSize(2);
    }

    @Test
    @DisplayName("점수 기록 시 점수의 합이 볼링 핀의 합을 초과하면 예외가 발생하는지 여부 검증")
    void recordTest2() {
        Scores scores = new Scores();
        assertThatThrownBy(() -> {
            scores.record(RuleConfig.NUMBER_OF_PIN + 1);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("시도횟수 조회 함수가 제대로 동작하는지 테스트")
    void getTryCountTest() {
        Scores scores = new Scores();
        assertThat(scores.getTryCount()).isEqualTo(0);
        scores.record(0);
        assertThat(scores.getTryCount()).isEqualTo(1);
        scores.record(0);
        assertThat(scores.getTryCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("스트라이크 여부 판단 함수 검증 : 성공")
    void isStrikeTest1() {
        Scores scores = new Scores();
        scores.record(10);
        assertThat(scores.isStrike()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 여부 판단 함수 검증 : 실패")
    void isStrikeTest2() {
        Scores scores = new Scores();
        scores.record(5);
        scores.record(5);
        assertThat(scores.isStrike()).isFalse();
    }


    @Test
    @DisplayName("스페어 여부 판단 함수 검증 : 성공")
    void isSpareTest1() {
        Scores scores = new Scores();
        scores.record(5);
        scores.record(5);
        assertThat(scores.isSpare()).isTrue();
    }

    @Test
    @DisplayName("스페어 여부 판단 함수 검증 : 실패")
    void isSpareTest2() {
        Scores scores1 = new Scores();
        scores1.record(5);
        scores1.record(4);
        assertThat(scores1.isSpare()).isFalse();

        Scores scores2 = new Scores();
        scores2.record(10);
        assertThat(scores2.isSpare()).isFalse();
    }

    @Test
    @DisplayName("투구 시도 횟수가 초과되었는지 확인하는 함수 검증")
    void tryOverTest() {
        Scores scores = new Scores();
        assertThat(scores.tryOver()).isFalse();
        scores.record(0);
        assertThat(scores.tryOver()).isFalse();
        scores.record(0);
        assertThat(scores.tryOver()).isTrue();
    }

    @Test
    @DisplayName("일반 점수 조회 함수 검증")
    void getScoreTest() {
        Scores scores = new Scores();
        assertThat(scores.sumOfDownPins()).isEqualTo(0);
        scores.record(4);
        assertThat(scores.sumOfDownPins()).isEqualTo(4);
        scores.record(6);
        assertThat(scores.sumOfDownPins()).isEqualTo(4 + 6);
    }

}