package bowling;

import bowling.domain.Frames;
import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.ResultView;

/**
 * Created : 2020-12-16 오전 7:52
 * Developer : Seo
 */
public class Application {
    public static void main(String[] args) {
        User user = InputView.getUsers();
        Frames frames = new Frames();
        ResultView.print(user, frames);
    }
}
