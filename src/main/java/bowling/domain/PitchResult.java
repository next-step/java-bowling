package bowling.domain;

public enum PitchResult {
    STRIKE,     // 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
    SPARE,      // 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
    MISS,       // 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
    GUTTER,     // 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
    NONE
    ;

    public static PitchResult of(int restPin, int downPin, int restPitchCount) {
        if(isGutter(downPin)){
            return GUTTER;
        }
        if(isStrike(restPin, restPitchCount)){
            return STRIKE;
        }
        if(isSpare(restPin, restPitchCount)){
            return SPARE;
        }
        if(isMiss(restPin, restPitchCount)){
            return MISS;
        }
        return NONE;
    }

    private static boolean isStrike(int restPin, int restPitchCount){
        return restPin == 0 && restPitchCount != 0;
    }

    private static boolean isSpare(int restPin, int restPitchCount){
        return restPin == 0 && restPitchCount == 0;
    }

    private static boolean isMiss(int restPin, int restPitchCount){
        return restPin != 0 && restPitchCount == 0;
    }

    private static boolean isGutter(int downPin){
        return downPin == 0;
    }

}
