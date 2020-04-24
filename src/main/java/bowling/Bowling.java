package bowling;

import java.util.ArrayList;
import java.util.List;

public class Bowling {
    private List<Integer> falledPins = new ArrayList<>();

    public List<String> roll(int falledPinCount) {
        falledPins.add(falledPinCount);

        return createResult(falledPins);
    }

    public List<String> createResult(List<Integer> falledPins) {
        List<String> result = new ArrayList<>();
        int leftSelectCount = 1;
        int falledPinsBefore = 0;

        for (int countOfPins : falledPins) {
            if (countOfPins != 10) {
                if (leftSelectCount == 0) {
                    if (falledPinsBefore + countOfPins == 10) {
                        result.add(falledPinsBefore + "|/");
                    } else {
                        result.add(falledPinsBefore + "|" + (countOfPins == 0 ? "-" : countOfPins));
                    }
                    leftSelectCount = 1;
                } else {
                    falledPinsBefore = countOfPins;
                    leftSelectCount = 0;
                }
            }
            if (countOfPins == 10) {
                result.add("X");
            }
        }

        return result;
    }
}
