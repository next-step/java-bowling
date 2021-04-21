package qna.domain;

import java.util.Collections;
import java.util.List;

public class DeleteHistories {

  private final List<DeleteHistory> deleteHistories;

  public DeleteHistories(List<DeleteHistory> deleteHistoryList) {
    this.deleteHistories = deleteHistoryList;
  }

  public List<DeleteHistory> histories() {
    return Collections.unmodifiableList(deleteHistories);
  }
}
