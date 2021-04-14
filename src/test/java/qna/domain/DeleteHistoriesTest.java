//package qna.domain;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static qna.domain.QuestionTest.Q1;
//import static qna.domain.QuestionTest.Q2;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class DeleteHistoriesTest {
//
//  private DeleteHistories deleteHistories;
//
//  @BeforeEach
//  void setUp() {
//    deleteHistories = new DeleteHistories();
//  }
//
//  @Test
//  void add_history_test() {
//    deleteHistories.add(new DeleteHistory(Q1, LocalDateTime.now()));
//
//    int size = deleteHistories.history().size();
//
//    assertThat(size).isEqualTo(1);
//  }
//
//  @Test
//  void addAll_history_test() {
//    deleteHistories.addAll(Arrays.asList(new DeleteHistory(Q1, LocalDateTime.now()), new DeleteHistory(Q2,LocalDateTime.now())));
//
//    int size = deleteHistories.history().size();
//
//    assertThat(size).isEqualTo(2);
//  }
//
//}
