package bowling.domain;

import bowling.view.LastFrameString;
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

    @DisplayName("스페어도 못 쳤을 시 테스트.")
    @Test
    public void NotSpareTest() {
        //given
        LastFrame lastFrame = new LastFrame();
        lastFrame.playShot(new Shot(1));
        lastFrame.playShot(new Shot(7));

        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(true);

    }

    @DisplayName("스페어 쳤을 때 테스트")
    @Test
    public void spareTest() {
        //given
        LastFrame lastFrame = new LastFrame();
        lastFrame.playShot(new Shot(1));
        lastFrame.playShot(new Shot(9));

        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(false);
    }

    @DisplayName("스페어 치고 한번 더 던졌을때")
    @Test
    public void spareAndOneShotTest() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.playShot(new Shot(1));
        lastFrame.playShot(new Shot(9));
        lastFrame.playShot(new Shot(8));

        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(true);
    }

    @DisplayName("스페어 치고 스트라이크 일때")
    @Test
    public void spareAndStrikeTest() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.playShot(new Shot(1));
        lastFrame.playShot(new Shot(9));
        lastFrame.playShot(new Shot(8));

        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(true);
    }

    @DisplayName("원스트라이크 일때")
    @Test
    public void oneStrikeTest() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.playShot(new Shot(10));

        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(false);
    }

    @DisplayName("원스트라이크하고 한번더 던졌을때 일때")
    @Test
    public void oneStrikeAndOneShotTest() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.playShot(new Shot(10));
        lastFrame.playShot(new Shot(4));
        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(false);
    }

    @DisplayName("원스트라이크하고 두번 더 던졌을때 일때")
    @Test
    public void oneStrikeAndTwoShotTest() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.playShot(new Shot(10));
        lastFrame.playShot(new Shot(4));
        lastFrame.playShot(new Shot(4));
        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(true);
    }

    @DisplayName("원스트라이크하고 스페어 던졌을때 일때")
    @Test
    public void oneStrikeAndSpareTest() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.playShot(new Shot(10));
        lastFrame.playShot(new Shot(4));
        lastFrame.playShot(new Shot(6));
        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(true);
    }

    @DisplayName("더블 스트라이크 했을 때 테스트.")
    @Test
    public void doubleStrikeTest() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.playShot(new Shot(10));
        lastFrame.playShot(new Shot(10));

        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(false);
    }

    @DisplayName("더블 스트라이크하고 샷한번 더 던졌을 때 테스트.")
    @Test
    public void doubleStrikeAndOneShotTest() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.playShot(new Shot(10));
        lastFrame.playShot(new Shot(10));
        lastFrame.playShot(new Shot(5));

        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(true);
    }

    @DisplayName("트리플 스트라이크 테스트")
    @Test
    public void tripleStrikeTest() {
        LastFrame lastFrame = new LastFrame();

        lastFrame.playShot(new Shot(10));
        lastFrame.playShot(new Shot(10));
        lastFrame.playShot(new Shot(10));

        //when
        boolean isFinished = lastFrame.isFinished();

        //then
        assertThat(isFinished).isEqualTo(true);
    }

}
