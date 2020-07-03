package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MultiUserFrames: 여러명의 볼링게임 순서를 관리하는 클래스 테스트")
class MultiUserFramesTest {

    @DisplayName("진행 횟수에 따라서 올바른 프레임 넘버를 반환한다")
    @Test
    public void getCurrentFrameNo_ReturnFrameNo() {
        MultiUserFrames multiUserFrames = new MultiUserFrames();
        multiUserFrames.addPlayer(new Player("PJS"));
        multiUserFrames.addPlayer(new Player("KYJ"));

        multiUserFrames.bowling(new Pin(10));
        multiUserFrames.bowling(new Pin(10));
        multiUserFrames.bowling(new Pin(5));
        multiUserFrames.bowling(new Pin(5));
        multiUserFrames.bowling(new Pin(3));
        multiUserFrames.bowling(new Pin(3));
        multiUserFrames.bowling(new Pin(3));
        assertThat(multiUserFrames.getCurrentFrameNo()).isEqualTo(3);
    }
}
