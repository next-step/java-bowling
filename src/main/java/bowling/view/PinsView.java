package bowling.view;

public class PinsView extends InputView{

    private static final String GET_KNOCKED_DOWN_PIN_MESSAGE = "%d 프레임 투구 :";

    public static int getKnockedDownPinCount(int frameNo) {
        return getIntValue(String.format(GET_KNOCKED_DOWN_PIN_MESSAGE, frameNo));
    }
}
