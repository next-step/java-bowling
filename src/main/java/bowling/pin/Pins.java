package bowling.pin;

public class Pins {

    private static final int MAX_PIN_CNT = 10;
    private static final int MIN_PIN_CNT = 0;
    private final int cnt;

    public Pins(int cnt) {
        validate(cnt);
        this.cnt = cnt;
    }

    void validate(int cnt){
        if(cnt < MIN_PIN_CNT || cnt > MAX_PIN_CNT){
            throw new IllegalArgumentException("핀의 개수는 0~10만 가능합니다.");
        }
    }

    public boolean isStrike(){
        return cnt == MAX_PIN_CNT;
    }

    public static boolean isSpare(Pins first, Pins second){
        return first.cnt + second.cnt == MAX_PIN_CNT;
    }

    public static boolean isMiss(Pins first, Pins second){
        return first.cnt + second.cnt < MAX_PIN_CNT;
    }

    public static boolean isGutter(Pins first, Pins second){
        return first.cnt == MIN_PIN_CNT && second.cnt == MIN_PIN_CNT;
    }

    @Override
    public String toString(){
        return cnt+"개";
    }

    public String getSymbol() {
        if(cnt == 0){
            return "-";
        }
        return cnt+"";
    }
}
