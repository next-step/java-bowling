package bowling;

import org.junit.jupiter.api.Test;

/**
 * #### 투구
 * * 한 투구는 0..10의 쓰러뜨린 핀 수를 가진다
 * * 한 프레임내의 두번째 투구는 첫번재 투구의 핀수를 더해서 10을 초과할 수 없다
 * * 마지막 프레임의 두번째 투구는 첫번째 투구가 10개의 핀수를 가진다면 또다시 10개의 핀수를 가질 수 있다
 * * 마지막 프레임의 두번째 투구는 첫번째 투구가 10개 미만의 핀수를 가진다면 첫번째 투구의 핀수를 더해서 10을 초과할 수 없다
 * * 마지막 프레임의 세번째 투구는 첫번째 투구의 핀수가 10인경우에만 존재한다
 * * 마지막 프레임의 세번째 투구는 두번째 투구가 10개의 핀수를 가진다면 또다시 10개의 투구를 가질 수 있다.
 * * 마지막 프레임의 세번째 투구는 두번째 투구가 10개 미만의 핀수를 가진다면 두번째 투구의 핀수를 더해서 10을 초과할 수 없다
 */
public class BallThrowTest {
    @Test
    void create() {
        new BallThrow(0);
    }

    private static class BallThrow {
        public BallThrow(int fallingPins) {
        }
    }
}
