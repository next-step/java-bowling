package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

  private final List<DeleteHistory> deleteHistories = new ArrayList<>();

  public DeleteHistories() {
  }

  public DeleteHistories add(DeleteHistory deleteHistory) {
    deleteHistories.add(deleteHistory);
    return this;
  }

  public List<DeleteHistory> getDeleteHistories() {
    return Collections.unmodifiableList(deleteHistories);
  }
}
