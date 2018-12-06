package domain;

import exception.DontTryException;
import org.junit.Test;
import utils.StringUtils;

import static org.assertj.core.api.Assertions.assertThat;


public class NormalFrameTest {

    @Test
    public void 스페어() {
        NormalFrame normalFrame = new NormalFrame();
        Pin pin = Pin.of(5);
        normalFrame.addPin(pin);
        normalFrame.addPin(pin);

        assertThat(normalFrame.toString()).isEqualTo("5" + StringUtils.VERTICAL_BAR + StringUtils.SPARE);
    }

    @Test
    public void 스트라이크() {
        NormalFrame normalFrame = new NormalFrame();
        Pin pin = Pin.of(10);
        normalFrame.addPin(pin);
        assertThat(normalFrame.toString()).isEqualTo(StringUtils.STRIKE);
    }

    @Test(expected = DontTryException.class)
    public void 스트라이크이후던짐() {
        NormalFrame normalFrame = new NormalFrame();
        Pin pin = Pin.of(10);
        normalFrame.addPin(pin);
        normalFrame.addPin(pin);
    }


    @Test(expected = DontTryException.class)
    public void 총합스페어이상() {
        NormalFrame normalFrame = new NormalFrame();
        Pin pin = Pin.of(6);
        Pin pin1 = Pin.of(5);
        normalFrame.addPin(pin);
        normalFrame.addPin(pin1);
    }


    @Test
    public void 스트라이크패스여부() {
        NormalFrame normalFrame = new NormalFrame();
        Pin pin = Pin.of(10);
        normalFrame.addPin(pin);

        assertThat(normalFrame.isStrikePass()).isTrue();
    }

    @Test
    public void 넥스트프레임테스트() {
        NormalFrame normalFrame = new NormalFrame();
        Frame nextFrame;
        Pin pin = Pin.of(10);
        normalFrame.addPin(pin);
        nextFrame = normalFrame.nextFrame();

        assertThat(nextFrame.isStrikePass()).isFalse();
    }


    @Test
    public void 프레임10개파이널검사() {
        Frame nextFrame = null;
        NormalFrame normalFrame = new NormalFrame(1);
        for (int i = 1; i < Frame.MAX_FRAME; i++) {
            nextFrame = normalFrame.nextFrame();
        }
        assertThat(nextFrame.isFinal()).isTrue();
    }

    @Test
    public void 프레임9개노말검사() {
        Frame nextFrame;
        NormalFrame normalFrame = new NormalFrame(1);
        for (int i = 2; i < Frame.MAX_FRAME; i++) {
            nextFrame = normalFrame.nextFrame();
            assertThat(nextFrame.isFinal()).isFalse();
        }
    }
}