package bowling.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class FinalFrameTest {
    @Test
    void 미스_점수() {
        Assertions.assertThat(new FinalFrame().bowl(5).bowl(4).getScore()).isEqualTo(9);
    }

    @Test
    void 스트라이크_스페어_점수() {
        Assertions.assertThat(new FinalFrame().bowl(10).bowl(5).bowl(5).getScore()).isEqualTo(20);
    }

    @Test
    void 투스트라이크_점수() {
        Assertions.assertThat(new FinalFrame().bowl(10).bowl(10).bowl(5).getScore()).isEqualTo(25);
    }

    @Test
    void 쓰리스트라이크_점수() {
        Assertions.assertThat(new FinalFrame().bowl(10).bowl(10).bowl(10).getScore()).isEqualTo(30);
    }

    @Test
    void _9프레임_스트라이크() {
        Score score = Score.ofStrike();
        Assertions.assertThat(new FinalFrame().bowl(10).bowl(10).calculateAddScore(score).getScore()).isEqualTo(30);
    }

    @Test
    void _9프레임_스페어() {
        Score score = Score.ofSpare();
        Assertions.assertThat(new FinalFrame().bowl(5).bowl(5).calculateAddScore(score).getScore()).isEqualTo(15);
    }


}