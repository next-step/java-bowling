package bowling;


import bowling.application.BowlingService;
import bowling.application.Request;
import bowling.application.Response;
import bowling.domain.state.Pin;
import bowling.ui.BowlingController;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Application {

    public static void main(String[] args) {
        Request request = new Request(InputView.inputName());

        BowlingController bowlingController = new BowlingController(new BowlingService());

        Response response = bowlingController.bowl(request);
        ResultView.view(response);

        while (!response.isEnd()) {
            Pin pin = new Pin(InputView.inputBowl(response.getName()));
            request = request.bowlFallenPins(pin);
            response = bowlingController.bowl(request);
            ResultView.view(response);
        }
    }
}
