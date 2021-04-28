package bowling;

import bowling.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PinResultTest {
    @Test
    @DisplayName("스트라이크 생성")
    public void createStrike(){
        Pin fallenPin = new Pin(10);

        PinResult firstPinResult = new FirstPinResult();

        assertThat(firstPinResult.pinResult(fallenPin).equals(new Strike(fallenPin))).isTrue();
    }

    @Test
    @DisplayName("첫 투구 일반 점수 생성")
    public void createNormalScore(){
        Pin fallenPin = new Pin(5);

        PinResult firstPinResult = new FirstPinResult();

        assertThat(firstPinResult.pinResult(fallenPin).equals(new NormalScore(fallenPin))).isTrue();
    }

    @Test
    @DisplayName("스페어 생성")
    public void createSpare(){
        NormalScore normalScore = new NormalScore(new Pin(5));

        Pin fallenPin = new Pin(5);

        PinResult secondPinResult = new SecondPinResult(normalScore.pin());

        assertThat(secondPinResult.pinResult(fallenPin).equals(new Spare(normalScore.pin(), fallenPin))).isTrue();
    }


    @Test
    @DisplayName("미스 생성")
    public void createMiss(){
        NormalScore normalScore = new NormalScore(new Pin(5));

        Pin fallenPin = new Pin(4);

        PinResult secondPinResult = new SecondPinResult(normalScore.pin());

        assertThat(secondPinResult.pinResult(fallenPin).equals(new Miss(normalScore.pin(), fallenPin))).isTrue();
    }
}
