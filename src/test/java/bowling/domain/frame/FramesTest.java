package bowling.domain.frame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FramesTest {

    @Test
    @DisplayName("현재 프레임에 쓰러트린 핀 개수를 추가한다.")
    public void addPin(){
        Frames frames = new Frames();
        frames.add(10);
        assertThat(frames.findFrame(0).fallenPin(0)).isEqualTo(10);
    }

    @Test
    @DisplayName("마지막 볼까지 던지면 false, 아니면 true 발생한다.")
    public void isNotFinalFrame(){
        Frames normalFrame = setFrames(9);
        Frames finalFrame = setFrames(10);
        Assertions.assertAll(
                () -> assertThat(normalFrame.isNotFinalFrame()).isTrue(),
                () -> assertThat(finalFrame.isNotFinalFrame()).isFalse()
        );
    }

    @Test
    @DisplayName("다음 프레임 번호 이동")
    public void nextFrame(){
        Frames frames = new Frames();
        assertThat(frames.frameNumber()).isEqualTo(1);
        frames.add(10);
        frames.nextFrame();
        assertThat(frames.frameNumber()).isEqualTo(2);
    }

    private Frames setFrames(int loopNumber){
        Frames frames = new Frames();
        for(int i=0; i<=loopNumber; i++){
            frames.add(10);
            frames.nextFrame();
        }
        if(loopNumber == 10){
            for(int i=0; i<2; i++){
                frames.add(10);
            }
        }

        return frames;
    }

}
