package bowling.domain;

public enum Result {
    STRIKE("X"), // 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
    SPARE("/"), // 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
    MISS(""), //  프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
    GUTTER("-");// 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시

    private String symbol;

    Result(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
