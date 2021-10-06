package step4;

import java.util.ArrayList;
import java.util.List;
import step4.view.InputView;
import step4.view.ResultView;

public class BowlingGameController {
    public static void run() {
        int numOfPeople = InputView.numOfPeople();
        List<String> personNames = new ArrayList<>();
        for (int i = 0; i < numOfPeople; i++) {
            String nameOfPerson = InputView.nameOfPerson();
            personNames.add(nameOfPerson);
        }

        for (int i = 0; i < 1; i++) {

            for (String personName : personNames) {
                InputView.throwBowl(personName);
                ResultView.printColumn();
                ResultView.printName(personName);
            }
        }

    }
}
