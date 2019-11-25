package game.bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by yusik on 2019/11/20.
 */
class FinalFrameTest {

    @DisplayName("frame open")
    @Test
    void miss() {

        // given
        FinalFrame finalFrame = new FinalFrame(10);

        // when
        finalFrame.bowl(1);
        finalFrame.bowl(2);
        System.out.println(finalFrame.getScore());

        // then
        assertThat(finalFrame.isFinish()).isTrue();
    }

    @DisplayName("1투구 스트라이크")
    @Test
    void strikeThreeTime() {

        // given
        FinalFrame finalFrame = new FinalFrame(10);

        // when
        finalFrame.bowl(10);
        System.out.println(finalFrame.getScore());

        // then
        assertThat(finalFrame.isFinish()).isFalse();
    }

    @DisplayName("2투구 스트라이크")
    @Test
    void secondThrowStrike() {

        // given
        FinalFrame finalFrame = new FinalFrame(10);

        // when
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        System.out.println(finalFrame.getScore());

        // then
        assertThat(finalFrame.isFinish()).isFalse();
    }

    @DisplayName("3투구 스트라이크")
    @Test
    void thirdThrowStrike() {

        // given
        FinalFrame finalFrame = new FinalFrame(10);

        // when
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        System.out.println(finalFrame.getScore());

        // then
        assertThat(finalFrame.isFinish()).isTrue();
    }

    @DisplayName("2투구 스페어")
    @Test
    void secondThrowSpare() {

        // given
        FinalFrame finalFrame = new FinalFrame(10);

        // when
        finalFrame.bowl(9);
        finalFrame.bowl(1);
        System.out.println(finalFrame.getScore());

        // then
        assertThat(finalFrame.isFinish()).isFalse();
    }

    @DisplayName("2투구 스페어-스트라이크")
    @Test
    void secondThrowSpareStrike() {

        // given
        FinalFrame finalFrame = new FinalFrame(10);

        // when
        finalFrame.bowl(9);
        finalFrame.bowl(1);
        finalFrame.bowl(10);
        System.out.println(finalFrame.getScore());

        // then
        assertThat(finalFrame.isFinish()).isTrue();
    }
}