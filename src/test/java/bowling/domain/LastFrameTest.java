package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@TestInstance(Lifecycle.PER_METHOD)
public class LastFrameTest {
    private LastFrame lastFrame;

    @BeforeEach
    public void init() {
        this.lastFrame = new LastFrame();
    }

    @DisplayName("처음 던지기 전 남은 갯수 테스트")
    @Test
    public void firstShotRemainTest() {
        //when
        int remain = lastFrame.remainPins();

        //then
        assertThat(remain).isEqualTo(10);
    }

    @DisplayName("두번째 던지기 전 남은 갯수 테스트")
    @Test
    public void secondShotRemainTest() {
        //given
        Shot shot = new Shot(5);
        lastFrame.playShot(shot);

        //when
        int remain = lastFrame.remainPins();

        //then
        assertThat(remain).isEqualTo(5);
    }

    @DisplayName("처음 던진 게 스트라이크 일 때 남은 갯수 테스트")
    @Test
    public void firstStrikeRemainTest() {
        //given
        Shot shot = new Shot(10);
        lastFrame.playShot(shot);

        //when
        int remain = lastFrame.remainPins();

        //then
        assertThat(remain).isEqualTo(10);
    }

    @DisplayName("처음 던진 게 스트라이크이고 두번째 샷은 스트라이크 아닐때 테스트")
    @Test
    public void firstStrikeAndOneShotRemainTest() {
        //given
        Shot shot01 = new Shot(10);
        Shot shot02 = new Shot(5);
        lastFrame.playShot(shot01);
        lastFrame.playShot(shot02);

        //when
        int remain = lastFrame.remainPins();

        //then
        assertThat(remain).isEqualTo(5);
    }

    @DisplayName("두번 던져서 스페어일때 남은 갯수 테스트")
    @Test
    public void spareRemainTest() {
        //given
        Shot shot01 = new Shot(7);
        Shot shot02 = new Shot(3);
        lastFrame.playShot(shot01);
        lastFrame.playShot(shot02);

        //when
        int remain = lastFrame.remainPins();

        //then
        assertThat(remain).isEqualTo(10);
    }

    @DisplayName("더블 했을 때 남은 갯수 테스트")
    @Test
    public void doubleStrikeRemainTest() {
        //given
        Shot shot01 = new Shot(10);
        Shot shot02 = new Shot(10);
        lastFrame.playShot(shot01);
        lastFrame.playShot(shot02);

        //when
        int remain = lastFrame.remainPins();

        //then
        assertThat(remain).isEqualTo(10);
    }
}
