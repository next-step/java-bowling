package bowling.domain;

public class CannotBeLessThanZero extends IllegalArgumentException{

    public CannotBeLessThanZero(int falledPins) {
        super(String.format("핀은 0 미만이 될 수 없습니다. 현재 쓰러트린 핀 : %d", falledPins));
    }
}
