package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {
    @DisplayName("마지막 프레임은 첫번째 투구가 Strike 면 투구 기회가 있다.")
    @Test
    void firstStrikeHasRemainChance() {
        FinalFrame finalFrame = new FinalFrame(10);

        finalFrame.bowl(10);

        assertThat(finalFrame.hasRemainChance()).isTrue();
    }

    @DisplayName("마지막 프레임은 두번째 투구가 Spare 면 투구 기회가 있다.")
    @Test
    void spareHasRemainChance() {
        FinalFrame finalFrame = new FinalFrame(10);

        finalFrame.bowl(8);
        finalFrame.bowl(2);

        assertThat(finalFrame.hasRemainChance()).isTrue();
    }

    @DisplayName("마지막 프레임은 두번째 투구가 Spare가 아니면 투구 기회가 없다")
    @Test
    void notSpareHasNotRemainChance() {
        FinalFrame finalFrame = new FinalFrame(10);

        finalFrame.bowl(8);
        finalFrame.bowl(1);

        assertThat(finalFrame.hasRemainChance()).isFalse();
    }

    @DisplayName("마지막 프레임은 보너스 점수 계산없이 더하기만 한다.")
    @Test
    void calculateFinalFrameScore() {
        Frame finalFrame = new FinalFrame(10);

        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        Score score = finalFrame.calculateScore().get();

        assertThat(score.getScore()).isEqualTo(30);
    }

    @DisplayName("9번 프레임이 보너스 점수가 필요하면 마지막 프레임도 보너스 계산 로직을 수행한다.")
    @Test
    void calculateBonusScore() {
        Frame nineFrame = new NormalFrame(9);
        Frame finalFrame = nineFrame.createNextFrame();

        nineFrame.bowl(10);

        assertThat(nineFrame.calculateScore()).isEmpty();

        finalFrame.bowl(1);
        finalFrame.bowl(9);
        assertThat(nineFrame.calculateScore()).isNotEmpty();
        assertThat(nineFrame.calculateScore().get().getScore()).isEqualTo(20);
    }
}