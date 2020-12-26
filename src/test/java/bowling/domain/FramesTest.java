package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.assertj.core.api.Assertions.assertThat;

public class FramesTest {

    private static final int MAX_FRAME_COUNT = 10;

    private static Frames frames;

    @BeforeEach
    void init(){
        frames = Frames.of();
    }


    @DisplayName("10개의 프레임 생성 및 모든 투구 완료 시 isEnd Test")
    @Test
    void isEndTest(){

        for (int i=0; i<MAX_FRAME_COUNT * 2; i++) {
            frames.execute(2);
            frames.makeNextFrames();
        }

        assertThat(frames.isEnd()).isTrue();
    }


    @DisplayName("한 프레임의 투구 기회가 끝나지 않은 경우, 다음 프레임 생성 안됨.")
    @Test
    void makeNextFrameWhenCurrentIsNotEnd(){
        frames.execute(2);
        int currentFrameSize = frames.getFrames().size();

        frames.makeNextFrames();

        assertThat(currentFrameSize).isEqualTo(frames.getFrames().size());
    }

    @DisplayName("strike, spare 인 경우 보너스 점수 획득 기회 생성 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10:true", "2,8:true", "2,7:false"}, delimiter = ':')
    void hasUnfinishedScoreTest(String pitchResultsWithComma, boolean expectValue){

        String[] pitchResults = pitchResultsWithComma.split(",");

        for (String pitchResult: pitchResults) {
            frames.execute(Integer.parseInt(pitchResult));
        }

        assertThat(frames.hasUnfinishedScore()).isEqualTo(expectValue);
    }
    
}
