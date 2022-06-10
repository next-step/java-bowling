package bowling.domain;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {
    @Test
    void 미스_점수() {
        assertThat(new FinalFrame().bowl(5).bowl(4).score()).isEqualTo(9);
    }

    @Test
    void 스트라이크_스페어_점수() {
        assertThat(new FinalFrame().bowl(10).bowl(5).bowl(5).score()).isEqualTo(20);
    }

    @Test
    void 투스트라이크_점수() {
        assertThat(new FinalFrame().bowl(10).bowl(10).bowl(5).score()).isEqualTo(25);
    }

    @Test
    void 쓰리스트라이크_점수() {
        assertThat(new FinalFrame().bowl(10).bowl(10).bowl(10).score()).isEqualTo(30);
    }

    @Test
    void _9프레임_스트라이크() {
        Score score = Score.ofStrike();
        assertThat(new FinalFrame().bowl(10).bowl(10).calculateScore(score).getScore()).isEqualTo(30);
    }

    @Test
    void _9프레임_스페어() {
        Score score = Score.ofSpare();
        assertThat(new FinalFrame().bowl(5).bowl(5).calculateScore(score).getScore()).isEqualTo(15);
    }
}