package bowling.exception;

public class CannotBeBiggerThanMax extends IllegalArgumentException {

    public CannotBeBiggerThanMax(int falledPins) {
        super(String.format("핀이 10개 보다 클 수 없습니다. 현재 쓰러트린 핀 : %d", falledPins));
    }
}
