package bowling;

public class CannotBowlException extends Exception{
    public CannotBowlException() {
        super("더이상 게임을 진행할 수 없습니다");
    }
}
