package bowling.domain.frame;

import bowling.domain.state.State;
import java.util.ArrayList;
import java.util.List;

public class LastFrame implements Frame {

    private final List<State> state;

    public LastFrame(List<State> state) {
        this.state = state;
    }

    public List<State> getState() {
        return state;
    }

    public String printStr() {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < state.size(); i++) {
            State state1 = state.get(i);
            resultList.add(state1.display().replaceAll(" ", ""));
        }

        String result = "";

        for (String s : resultList) {
            result = result + s;

        }

        return result;
    }
}
