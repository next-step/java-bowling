package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GeneralFrameTest extends FrameTest {

    @BeforeEach
    void setUp(){
        frame = new GeneralFrame(1, null);
    }

    @DisplayName("1번 투구에 10개 핀을 쓰러뜨리면 프레임을 종료된다")
    @Test
    void isEnd1(){
        frame.inputPins(10);
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("2번 투구하면 프래임은 종료된다")
    @Test
    void isEnd2(){
        frame.inputPins(5);
        frame.inputPins(4);

        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("1~9번째 프레임은 GeneralFrame 이다")
    @Test
    void should_be_GeneralFrame(){
        Frame first = Frame.first();
        assertThat(first instanceof GeneralFrame).isTrue();

        Frame second = first.nextFrame();
        assertThat(second instanceof GeneralFrame).isTrue();

        Frame third = second.nextFrame();
        assertThat(third instanceof GeneralFrame).isTrue();

        Frame forth = third.nextFrame();
        assertThat(forth instanceof GeneralFrame).isTrue();

        Frame fifth = forth.nextFrame();
        assertThat(fifth instanceof GeneralFrame).isTrue();

        Frame sixth = fifth.nextFrame();
        assertThat(sixth instanceof GeneralFrame).isTrue();

        Frame seventh = sixth.nextFrame();
        assertThat(seventh instanceof GeneralFrame).isTrue();

        Frame eighth = seventh.nextFrame();
        assertThat(eighth instanceof GeneralFrame).isTrue();

        Frame nineth = eighth.nextFrame();
        assertThat(nineth instanceof GeneralFrame).isTrue();
    }

}