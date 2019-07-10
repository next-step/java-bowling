package bowling.domain;

import java.util.Arrays;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-10 19:10
 */

/*
    스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
    스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
    미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
    거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
 */
public enum Bowl {
    GUTTER(0, "-", false),
    MISS_ONE(1, "1", false),
    MISS_TWO(2, "2", false),
    MISS_THREE(3, "3", false),
    MISS_FOUR(4, "4", false),
    MISS_FIVE(5, "5", false),
    MISS_SIX(6, "6", false),
    MISS_SEVEN(7, "7", false),
    MISS_EIGHT(8, "8", false),
    MISS_NINE(9, "9", false),
    SPARE(10, "/", false),
    STRIKE(10, "X", true);

    private int score;
    private String scoreDisplay;
    private boolean isStrike;

    Bowl(int score, String scoreDisplay, boolean isStrike) {
        this.score = score;
        this.scoreDisplay = scoreDisplay;
        this.isStrike = isStrike;
    }

    public static Bowl check(int score, boolean isStrike) {
        return Arrays.stream(Bowl.values())
                .filter(bowl -> bowl.score == score && bowl.isStrike == isStrike)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getScoreDisplay() {
        return scoreDisplay;
    }
}
