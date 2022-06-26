package bowling.domain.frame;

import bowling.domain.state.*;
import bowling.exception.InvalidScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {
    @DisplayName("첫번째 투구과 두번째 투구의 값을 합쳤을 때 10이 나오면 스페어 객체가 생성된다.")
    @Test
    void delivery_spare() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(1);
        abstractFrame.bowl(9);

        assertThat(abstractFrame.getSecondHalfFrameState()).isEqualTo(new Spare(1, 9));
    }

    @DisplayName("첫번째 투구과 두번째 투구의 값을 합쳤을 때 10보다 작으면 Miss 객체가 생성된다.")
    @Test
    void delivery_miss() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(1);
        abstractFrame.bowl(7);

        assertThat(abstractFrame.getSecondHalfFrameState()).isEqualTo(new Miss(1, 7));
    }

    @DisplayName("첫번째 투구가 10이면 스트라이크 객체가 생성된다.")
    @Test
    void delivery_strike() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(10);

        assertThat(abstractFrame.getFirstHalfFrameState()).isEqualTo(new Strike());
    }

    @DisplayName("첫번째 투구에 입력된 수가 10 초과일경우 에러가 발생한다.")
    @Test
    void delivery_over10atFirstBowl() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(1);
        assertThatThrownBy(() -> abstractFrame.bowl(11))
                .isInstanceOf(InvalidScoreException.class)
                .hasMessageContaining("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
    }

    @DisplayName("두번째 투구에 입력된 수가 10 초과일경우 에러가 발생한다.")
    @Test
    void delivery_over10atSecondBowl() {
        AbstractFrame abstractFrame = new NormalFrame();
        assertThatThrownBy(() -> abstractFrame.bowl(11))
                .isInstanceOf(InvalidScoreException.class)
                .hasMessageContaining("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
    }

    @DisplayName("첫번째 투구와 두번째 투구에 입력된 수의 합이 10 초과일경우 에러가 발생한다.")
    @Test
    void delivery_over10atTotalBowl() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(5);
        assertThatThrownBy(() -> abstractFrame.bowl(6))
                .isInstanceOf(InvalidScoreException.class)
                .hasMessageContaining("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
    }

    @DisplayName("Ready 상태(투구를 한번도 하지 않았을 때) 추가 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_ready() {
        AbstractFrame abstractFrame = new NormalFrame();
        assertThat(abstractFrame.getFirstHalfFrameState()).isEqualTo(new Ready());
        assertThat(abstractFrame.capableOfAdditionalBowling()).isTrue();
    }

    @DisplayName("fistBowl 상태(첫 투구를 진행하였을 때) 추가 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_fistBowl() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(1);

        assertThat(abstractFrame.getFirstHalfFrameState()).isEqualTo(new FirstBowl(1));
        assertThat(abstractFrame.capableOfAdditionalBowling()).isTrue();
    }

    @DisplayName("strike 상태일때 추가 투구가 불가능한지 확인")
    @Test
    void additionallyDeliverable_strike() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(10);

        assertThat(abstractFrame.getFirstHalfFrameState()).isEqualTo(new Strike());
        assertThat(abstractFrame.capableOfAdditionalBowling()).isFalse();
    }

    @DisplayName("spare 상태 추가 투구가 불가능한지 확인")
    @Test
    void additionallyDeliverable_spare() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(9);
        abstractFrame.bowl(1);

        assertThat(abstractFrame.getSecondHalfFrameState()).isEqualTo(new Spare(9, 1));
        assertThat(abstractFrame.capableOfAdditionalBowling()).isFalse();
    }

    @DisplayName("miss 상태 추가 투구가 불가능한지 확인")
    @Test
    void additionallyDeliverable_miss() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(5);
        abstractFrame.bowl(4);

        assertThat(abstractFrame.getSecondHalfFrameState()).isEqualTo(new Miss(5, 4));
        assertThat(abstractFrame.capableOfAdditionalBowling()).isFalse();
    }

    @DisplayName("점수를 출력한다.")
    @Test
    void getScore() {
        AbstractFrame abstractFrame = new NormalFrame();
        abstractFrame.bowl(3);
        abstractFrame.bowl(3);

        assertThat(abstractFrame.getScore()).isEqualTo(6);
    }

    @DisplayName("Frame이 추가 점수가 필요할 경우 next에서 결과 도출")
    @Test
    void getScore_spare() {
        FrameLinkedList frameLinkedList = new FrameLinkedList();
        frameLinkedList.add(new NormalFrame());
        AbstractFrame abstractFrame1 = frameLinkedList.get();
        abstractFrame1.bowl(3);
        abstractFrame1.bowl(7);
        AbstractFrame abstractFrame2 = abstractFrame1.next;
        abstractFrame2.bowl(5);
        abstractFrame2.bowl(1);

        int score1 = abstractFrame1.getScore();
        assertThat(score1).isEqualTo(15);
        int score2 = abstractFrame2.getScore();
        assertThat(score2).isEqualTo(6);
    }
}
