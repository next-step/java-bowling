package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameInfoTest {

    @DisplayName("blank FrameInfo 는 unknown score 를 return 한다")
    @Test
    void blank(){
        FrameInfo blank = FrameInfo.blank(1);
        assertThat(blank.getScore()).isEqualTo(FrameScore.unknown);
    }


}