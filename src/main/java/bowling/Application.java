package bowling;

import bowling.domain.Frames;
import bowling.domain.Users;
import bowling.view.InputView;
import bowling.view.ResultView;

/**
 * Created : 2020-12-16 오전 7:52
 * Developer : Seo
 */
public class Application {
    public static final int FIRST_FRAME = 1;
    public static final int LAST_FRAME = 10;

    public static void main(String[] args) {
        Users users = InputView.getUsers();
        Frames frames = new Frames(users);
        ResultView.print(frames, users);

        for (int frameNo = FIRST_FRAME; frameNo < LAST_FRAME + 1; frameNo++) {
            frames.bowl(frameNo);
        }
    }
}
