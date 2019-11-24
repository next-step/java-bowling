package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.seok2.bowling.pin.domain.Pin;
import com.seok2.bowling.state.domain.Spare;
import com.seok2.bowling.state.domain.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScorePublisherTest {

    private final ScorePublisher publisher = new ScorePublisher();

    @Test
    @DisplayName("구독 신청")
    void subscribe() {
        FrameScore.of(publisher,Strike.of());
        assertThat(publisher.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("구독 취소")
    void unsubscribe() {
        FrameScore score = FrameScore.of(publisher,Strike.of());
        publisher.unsubscribe(score);
        assertThat(publisher.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("새로운 결과가 입력되면 구독자에게 값을 전파한다. (Spare)")
    void updateSpare() {
        FrameScore score = FrameScore.of(publisher, Spare.of(Pin.of(7)));
        publisher.update(Pin.of(7));
        assertThat(score.isCalculated()).isTrue();
        assertThat(score.getScore()).isEqualTo(Score.of(17));
    }

    @Test
    @DisplayName("새로운 결과가 입력되면 구독자에게 값을 전파한다. (Strike)")
    void updateStrike() {
        FrameScore score = FrameScore.of(publisher, Strike.of());
        publisher.update(Pin.of(10));
        assertThat(score.isCalculated()).isFalse();
        publisher.update(Pin.of(10));
        assertThat(score.isCalculated()).isTrue();
        assertThat(score.getScore()).isEqualTo(Score.of(30));
    }
}