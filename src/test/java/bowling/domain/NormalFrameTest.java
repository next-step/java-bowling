package bowling.domain;

import bowling.exception.InvalidScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {
    @DisplayName("첫번째 투구과 두번째 투구의 값을 합쳤을 때 10이 나오면 스페어 객체가 생성된다.")
    @Test
    void delivery_spare() {
        Frame frame = new NormalFrame();
        frame.delivery(1);
        frame.delivery(9);

        assertThat(frame.getSecondState()).isEqualTo(new Spare(1, 9));
    }

    @DisplayName("첫번째 투구과 두번째 투구의 값을 합쳤을 때 10보다 작으면 Miss 객체가 생성된다.")
    @Test
    void delivery_miss() {
        Frame frame = new NormalFrame();
        frame.delivery(1);
        frame.delivery(7);

        assertThat(frame.getSecondState()).isEqualTo(new Miss(1, 7));
    }

    @DisplayName("첫번째 투구가 10이면 스트라이크 객체가 생성된다.")
    @Test
    void delivery_strike() {
        Frame frame = new NormalFrame();
        frame.delivery(10);

        assertThat(frame.getFirstState()).isEqualTo(new Strike());
    }

    @DisplayName("첫번째 투구에 입력된 수가 10 초과일경우 에러가 발생한다.")
    @Test
    void delivery_over10atFirstBowl() {
        Frame frame = new NormalFrame();
        frame.delivery(1);
        assertThatThrownBy(() -> frame.delivery(11))
                .isInstanceOf(InvalidScoreException.class)
                .hasMessageContaining("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
    }

    @DisplayName("두번째 투구에 입력된 수가 10 초과일경우 에러가 발생한다.")
    @Test
    void delivery_over10atSecondBowl() {
        Frame frame = new NormalFrame();
        assertThatThrownBy(() -> frame.delivery(11))
                .isInstanceOf(InvalidScoreException.class)
                .hasMessageContaining("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
    }

    @DisplayName("첫번째 투구와 두번째 투구에 입력된 수의 합이 10 초과일경우 에러가 발생한다.")
    @Test
    void delivery_over10atTotalBowl() {
        Frame frame = new NormalFrame();
        frame.delivery(5);
        assertThatThrownBy(() -> frame.delivery(6))
                .isInstanceOf(InvalidScoreException.class)
                .hasMessageContaining("핀을 쓰러뜨린 수는 10을 초과할 수 없습니다.");
    }

    @DisplayName("Ready 상태(투구를 한번도 하지 않았을 때) 추가 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_ready() {
        Frame frame = new NormalFrame();
        assertThat(frame.getFirstState()).isEqualTo(new Ready());
        assertThat(frame.additionallyDeliverable()).isTrue();
    }

    @DisplayName("fistBowl 상태(첫 투구를 진행하였을 때) 추가 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_fistBowl() {
        Frame frame = new NormalFrame();
        frame.delivery(1);

        assertThat(frame.getFirstState()).isEqualTo(new FirstBowl(1));
        assertThat(frame.additionallyDeliverable()).isTrue();
    }

    @DisplayName("strike 상태일때 추가 투구가 불가능한지 확인")
    @Test
    void additionallyDeliverable_strike() {
        Frame frame = new NormalFrame();
        frame.delivery(10);

        assertThat(frame.getFirstState()).isEqualTo(new Strike());
        assertThat(frame.additionallyDeliverable()).isFalse();
    }

    @DisplayName("spare 상태 추가 투구가 불가능한지 확인")
    @Test
    void additionallyDeliverable_spare() {
        Frame frame = new NormalFrame();
        frame.delivery(9);
        frame.delivery(1);

        assertThat(frame.getSecondState()).isEqualTo(new Spare(9, 1));
        assertThat(frame.additionallyDeliverable()).isFalse();
    }

    @DisplayName("miss 상태 추가 투구가 불가능한지 확인")
    @Test
    void additionallyDeliverable_miss() {
        Frame frame = new NormalFrame();
        frame.delivery(5);
        frame.delivery(4);

        assertThat(frame.getSecondState()).isEqualTo(new Miss(5, 4));
        assertThat(frame.additionallyDeliverable()).isFalse();
    }

    @DisplayName("점수를 출력한다.")
    @Test
    void getScore() {
        Frame frame = new NormalFrame();
        frame.delivery(3);
        frame.delivery(3);

        assertThat(frame.getScore()).isEqualTo(6);
    }

    @DisplayName("Frame이 추가 점수가 필요할 경우 next에서 결과 도출")
    @Test
    void getScore_spare() {
        FrameLinkedList frameLinkedList = new FrameLinkedList();
        frameLinkedList.add(new NormalFrame());
        Frame frame1 = frameLinkedList.get();
        frame1.delivery(3);
        frame1.delivery(7);
        Frame frame2 = frame1.next;
        frame2.delivery(5);
        frame2.delivery(1);

        int score1 = frame1.getScore();
        assertThat(score1).isEqualTo(15);
        int score2 = frame2.getScore();
        assertThat(score2).isEqualTo(6);
    }
}
