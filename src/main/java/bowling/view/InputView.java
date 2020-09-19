package bowling.view;

import bowling.util.InputUtil;

public class InputView {
    public String getParticipants() {
        return InputUtil.returnString("플레이어 이름은(3 english letters)?:");
    }

    public int getHit(int currentFrame) {
        return InputUtil.returnInteger(currentFrame +"프레임 투구 : ");
    }

}
