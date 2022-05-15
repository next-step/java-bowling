package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {

  private DeleteHistories histories;
  private DeleteHistory questionDeleteHistory;
  private DeleteHistory answerDeleteHistory;

  @BeforeEach
  void setup() {
    histories = new DeleteHistories();
    LocalDateTime deletedTime = LocalDateTime.now();
    questionDeleteHistory = new DeleteHistory(
        ContentType.QUESTION, 1L, UserTest.JAVAJIGI, deletedTime
    );
    answerDeleteHistory = new DeleteHistory(
        ContentType.ANSWER, 2L, UserTest.SANJIGI, deletedTime
    );
  }

  @Test
  void add_성공() {
    DeleteHistories expected = new DeleteHistories(List.of(questionDeleteHistory));
    histories.add(questionDeleteHistory);

    assertThat(histories).isEqualTo(expected);
  }

  @Test
  void addAll_성공() {
    DeleteHistories expected = new DeleteHistories(
        List.of(questionDeleteHistory, answerDeleteHistory)
    );
    histories.addAll(new DeleteHistories(List.of(questionDeleteHistory, answerDeleteHistory)));

    assertThat(histories).isEqualTo(expected);
  }

  @Test
  void toList_성공() {
    histories = new DeleteHistories(List.of(questionDeleteHistory, answerDeleteHistory));

    List<DeleteHistory> deleteHistoryList = histories.toList();

    assertThat(deleteHistoryList).contains(questionDeleteHistory, answerDeleteHistory);
  }

  @Test
  void from_성공() {
    DeleteHistories expected = new DeleteHistories(List.of(questionDeleteHistory));
    histories = DeleteHistories.from(questionDeleteHistory);

    assertThat(histories).isEqualTo(expected);
  }
}
