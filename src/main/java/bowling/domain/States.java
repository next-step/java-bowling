package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class States {

  private List<String> states;

  public States() {
    this.states = new ArrayList<>();
  }

  public void add(String state) {
    states.add(state);
  }

  public List<String> states() {
    return Collections.unmodifiableList(states);
  }
}
