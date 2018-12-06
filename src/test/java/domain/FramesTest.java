package domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {


    @Test
    public void 스트라이크올스트라이크() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addPin(Pin.of(10));
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addPin(Pin.of(10));
        finalFrame.addPin(Pin.of(10));
        finalFrame.addPin(Pin.of(10));
        List<Frame> frameList = new ArrayList<>();
        frameList.add(normalFrame);
        frameList.add(finalFrame);
        Frames frames = new Frames(frameList);
        assertThat(frames.toString()).isEqualTo("     X| X|X|X|      |      |      |      |      |      |      |      |");
    }

    @Test
    public void 스트라이크10올스트라이크() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addPin(Pin.of(10));
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addPin(Pin.of(10));
        finalFrame.addPin(Pin.of(10));
        finalFrame.addPin(Pin.of(10));
        List<Frame> frameList = new ArrayList<>();
        for (int i = 0; i < 9; i ++){
            frameList.add(normalFrame);
        }
        frameList.add(finalFrame);
        Frames frames = new Frames(frameList);
        assertThat(frames.toString()).isEqualTo("     X|     X|     X|     X|     X|     X|     X|     X|     X| X|X|X|");
    }


    @Test
    public void 스페어10올스페어() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addPin(Pin.of(5));
        normalFrame.addPin(Pin.of(5));
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.addPin(Pin.of(5));
        finalFrame.addPin(Pin.of(5));
        finalFrame.addPin(Pin.of(5));
        List<Frame> frameList = new ArrayList<>();
        for (int i = 0; i < 9; i ++){
            frameList.add(normalFrame);
        }
        frameList.add(finalFrame);
        Frames frames = new Frames(frameList);
        assertThat(frames.toString()).isEqualTo("   5|/|   5|/|   5|/|   5|/|   5|/|   5|/|   5|/|   5|/|   5|/| 5|/|5|");
    }

    @Test
    public void 스페어5올스페어() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addPin(Pin.of(5));
        normalFrame.addPin(Pin.of(5));
        List<Frame> frameList = new ArrayList<>();
        for (int i = 0; i < 5; i ++){
            frameList.add(normalFrame);
        }
        Frames frames = new Frames(frameList);
        assertThat(frames.toString()).isEqualTo("   5|/|   5|/|   5|/|   5|/|   5|/|      |      |      |      |      |");
    }


    @Test
    public void 텅빈배열() {
        List<Frame> frameList = new ArrayList<>();
        Frames frames = new Frames(frameList);
        assertThat(frames.toString()).isEqualTo("      |      |      |      |      |      |      |      |      |      |");
    }
}