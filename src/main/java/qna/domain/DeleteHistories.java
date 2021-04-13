package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

  private final List<DeleteHistory> deleteHistories = new ArrayList<>();

  public void add(DeleteHistory deleteHistory) {
    deleteHistories.add(deleteHistory);
  }

  public List<DeleteHistory> history() {
    return Collections.unmodifiableList(deleteHistories);
  }
}
