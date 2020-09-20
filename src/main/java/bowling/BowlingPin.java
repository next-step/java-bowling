package bowling;

public class BowlingPin {
    private static final int INITIAl_PIN = 10;
    private int amountOfRemainPin;

    private BowlingPin(){
        amountOfRemainPin = INITIAl_PIN;
    }

    public static BowlingPin getInitialPin(){
        return new BowlingPin();
    }

    public void roll(int amountOfFallenPin){
        if(amountOfFallenPin > amountOfRemainPin){
            throw new IllegalArgumentException("exceed max pin Num");
        }
        amountOfRemainPin -= amountOfFallenPin;
    }

    public boolean isAllFallen(){
        return amountOfRemainPin == 0;
    }
}
