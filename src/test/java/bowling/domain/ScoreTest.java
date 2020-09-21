package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("점수 테스트")
public class ScoreTest {

    @DisplayName("일반 프레임 점수: NormalFrame -> Miss, Gutter")
    @Test
    public void normalFrame_miss_gutter() {
        NormalFrame first = NormalFrame.firstFrame();
        first.bowl(8).bowl(1);
        assertThat(first.getScore().isValid()).isTrue();
        assertThat(first.getScore().getScore()).isEqualTo(9);
    }

    @DisplayName("마지막 프레임 점수: FinalFrame -> Miss, Gutter")
    @Test
    public void finalFrame_miss_gutter() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(8).bowl(1);
        assertThat(finalFrame.getScore().isValid()).isTrue();
        assertThat(finalFrame.getScore().getScore()).isEqualTo(9);
    }

    @DisplayName("일반 프레임 스페어 점수: NormalFrame -> Spare")
    @Test
    public void normalFrame_spare() {
        NormalFrame first = NormalFrame.firstFrame();
        first.bowl(9).bowl(1).bowl(5);
        assertThat(first.getScore().isValid()).isTrue();
        assertThat(first.getScore().getScore()).isEqualTo(15);
    }

    @DisplayName("9번 프레임 스페어 점수: NormalFrame -> Spare")
    @Test
    public void normalFrame_9_spare() {
        NormalFrame nine = new NormalFrame(9);
        nine.bowl(9).bowl(1).bowl(5);
        assertThat(nine.getScore().isValid()).isTrue();
        assertThat(nine.getScore().getScore()).isEqualTo(15);
    }

    @DisplayName("9번 프레임 스트라이크 점수: NormalFrame -> Strike")
    @Test
    public void normalFrame_9_strike() {
        NormalFrame nine = new NormalFrame(9);
        nine.bowl(10).bowl(10).bowl(5);
        assertThat(nine.getScore().isValid()).isTrue();
        assertThat(nine.getScore().getScore()).isEqualTo(25);
    }

    @DisplayName("일반 프레임 스트라이크 점수: NormalFrame -> Strike")
    @Test
    public void normalFrame_strike() {
        NormalFrame first = NormalFrame.firstFrame();
        first.bowl(10).bowl(10).bowl(10);
        assertThat(first.getScore().isValid()).isTrue();
        assertThat(first.getScore().getScore()).isEqualTo(30);
    }

    @DisplayName("마지막 프레임 스페어 점수: FinalFrame -> Spare")
    @Test
    public void finalFrame_spare() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(9).bowl(1).bowl(5);
        assertThat(finalFrame.getScore().isValid()).isTrue();
        assertThat(finalFrame.getScore().getScore()).isEqualTo(15);
    }

    @DisplayName("마지막 프레임 스트라이크 점수: FinalFrame -> Strike")
    @Test
    public void finalFrame_srike() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(10).bowl(10).bowl(5);
        assertThat(finalFrame.getScore().isValid()).isTrue();
        assertThat(finalFrame.getScore().getScore()).isEqualTo(25);
    }

}