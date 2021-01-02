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

        Frame second = first.nextFrame();
        assertThat(second instanceof NormalFrame).isTrue();

        Frame third = second.nextFrame();
        assertThat(third instanceof NormalFrame).isTrue();

        Frame forth = third.nextFrame();
        assertThat(forth instanceof NormalFrame).isTrue();

        Frame fifth = forth.nextFrame();
        assertThat(fifth instanceof NormalFrame).isTrue();

        Frame sixth = fifth.nextFrame();
        assertThat(sixth instanceof NormalFrame).isTrue();

        Frame seventh = sixth.nextFrame();
        assertThat(seventh instanceof NormalFrame).isTrue();

        Frame eighth = seventh.nextFrame();
        assertThat(eighth instanceof NormalFrame).isTrue();

        Frame nineth = eighth.nextFrame();
        assertThat(nineth instanceof NormalFrame).isTrue();
    }

}