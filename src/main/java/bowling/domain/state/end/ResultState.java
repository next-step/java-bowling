package bowling.domain.state.end;

import bowling.domain.state.State;

public interface ResultState extends State {

    String getPrintMark();

    boolean isInstanceOf(Class<? extends ResultState> clazz);

}
