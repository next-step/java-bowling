package bowling.utils;

import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

class RandomIntNumberUtilTest {

    @RepeatedTest(100)
    void 범위_10_이하의_랜덤_숫자를_생성한다() {
        //given
        int number = RandomIntNumberUtil.nextInt(11);
        //when
        System.out.println("number = " + number);
        //then
        assertThat(number).isLessThanOrEqualTo(10);
    }

}
