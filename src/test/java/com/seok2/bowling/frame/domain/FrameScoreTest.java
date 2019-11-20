package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.seok2.bowling.pin.domain.Pin;
import com.seok2.bowling.state.domain.Miss;
import com.seok2.bowling.state.domain.Spare;
import com.seok2.bowling.state.domain.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameScoreTest {

    private final ScorePublisher publisher = new ScorePublisher();

    @Test
    @DisplayName("MISS 가 입력된 경우 점수 계산이 완료 된다.")
    void ofMiss() {
        assertThat(FrameScore.of(publisher, Miss.of(Pin.of(3) ,Pin.of(3))).isCalculated()).isTrue();
    }
    @Test
    @DisplayName("Strike 가 입력 된 경우 추가 점수 계산이 필요하다")
    void ofStrike() {
        assertThat(FrameScore.of(publisher, Strike.of()).isCalculated()).isFalse();
    }

    @Test
    @DisplayName("Spare 가 입력 된 경우 추가 점수 계산이 필요하다")
    void ofSpare() {
        assertThat(FrameScore.of(publisher, Spare.of(Pin.of(3))).isCalculated()).isFalse();
    }

    @Test
    @DisplayName("스코어를 직접 입력하여 생성하는경우")
    void ofScore() {
        assertThat(FrameScore.of(Score.TEN).getScore()).isEqualTo(Score.TEN);
    }
}