package bowling.domain.state.end;

import bowling.domain.state.State;

public interface EndState extends State {

    String getPrintMark();

    boolean isInstanceOf(Class<? extends EndState> clazz);

}
