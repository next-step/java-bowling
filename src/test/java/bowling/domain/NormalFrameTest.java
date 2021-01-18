package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest extends FrameTest {

    @BeforeEach
    void setUp(){
        frame = new NormalFrame(1);
    }

    @DisplayName("1번 투구에 10개 핀을 쓰러뜨리면 프레임을 종료된다")
    @Test
    void isEnd1(){
        frame.mark(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("2번 투구하면 프래임은 종료된다")
    @Test
    void isEnd2(){
        frame.mark(5);
        frame.mark(4);

        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("1~9번째 프레임은 GeneralFrame 이다")
    @Test
    void should_be_GeneralFrame(){
        Frame first = Frame.first();
        assertThat(first instanceof NormalFrame).isTrue();

        Frame second = first.createNext();
        assertThat(second instanceof NormalFrame).isTrue();

        Frame third = second.createNext();
        assertThat(third instanceof NormalFrame).isTrue();

        Frame forth = third.createNext();
        assertThat(forth instanceof NormalFrame).isTrue();

        Frame fifth = forth.createNext();
        assertThat(fifth instanceof NormalFrame).isTrue();

        Frame sixth = fifth.createNext();
        assertThat(sixth instanceof NormalFrame).isTrue();

        Frame seventh = sixth.createNext();
        assertThat(seventh instanceof NormalFrame).isTrue();

        Frame eighth = seventh.createNext();
        assertThat(eighth instanceof NormalFrame).isTrue();

        Frame nineth = eighth.createNext();
        assertThat(nineth instanceof NormalFrame).isTrue();
    }

    @DisplayName("1구만 던진 상태에서는 점수를 알 수 없다")
    @Test
    void unknownScore() {
        frame.mark(9);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("프레임을 miss 하면 다음 프레임에 관계 없이 점수를 계산한다")
    @Test
    void missScore() {
        Frame first = frame;
        first.mark(9);
        first.mark(0);

        assertThat(first.getScore()).isEqualTo(FrameScore.of(9));
    }

    @DisplayName("Strike 후 바로 점수를 알 수 없다")
    @Test
    void strike1() {
        frame.mark(10);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("Strike 후 1구(not strike)를 던져도 점수를 알 수 없다")
    @Test
    void strike2() {
        frame.mark(10);

        Frame next = frame.createNext();
        next.mark(8);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("Strike 후 1구(strike)를 던져도 점수를 알 수 없다")
    @Test
    void strike3() {
        frame.mark(10);

        Frame next = frame.createNext();
        next.mark(10);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("Strike 후 2구(strike, strike)를 던지면 점수를 알 수 있다")
    @Test
    void strike4() {
        frame.mark(10);

        Frame next1 = frame.createNext();
        next1.mark(10);

        Frame next2 = next1.createNext();
        next2.mark(10);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(30));
    }


    @DisplayName("Strike 후 2구(not strike)를 던지면 점수를 알 수 있다")
    @Test
    void strike5() {
        frame.mark(10);

        Frame next = frame.createNext();
        next.mark(8);
        next.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(19));
    }

    @DisplayName("Strike 후 2구(strike, not strike)를 던지면 점수를 알 수 있다")
    @Test
    void strike6() {
        frame.mark(10);

        Frame next1 = frame.createNext();
        next1.mark(10);

        Frame next2 = next1.createNext();
        next2.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(21));
    }

    @DisplayName("Spare 후 바로 점수를 알 수 없다")
    @Test
    void spare1() {
        frame.mark(9);
        frame.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("Spare 후 1구를 던지면 점수를 알 수 있다")
    @Test
    void spare2() {
        frame.mark(9);
        frame.mark(1);

        Frame next = frame.createNext();
        next.mark(8);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(18));
    }

    @DisplayName("9프레임이 Strike 고 마지막 프레임이 2구를 던지면 점수를 알수 있다")
    @Test
    void nineFrameScore1(){
        Frame frame = Frame.createNormal(9);
        frame.mark(10);

        Frame finalFrame = frame.createNext();
        finalFrame.mark(8);
        finalFrame.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(19));
    }

    @DisplayName("9프레임이 Strike 고 마지막 프레임에 보너스 투구까지 던지면 9,10 프레임 점수를 알 수 있다")
    @Test
    void nineFrameScore2(){
        Frame frame = Frame.createNormal(9);
        frame.mark(10);

        Frame finalFrame = frame.createNext();
        finalFrame.mark(8);
        finalFrame.mark(2);
        finalFrame.mark(10);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(20));
        assertThat(finalFrame.getScore()).isEqualTo(FrameScore.of(20));
    }

}