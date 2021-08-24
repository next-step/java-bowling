package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

  public static final Question Q1 = new Question("title1", "contents1").writeBy(
      UserTest.JAVAJIGI);
  public static final Question Q2 = new Question("title2", "contents2").writeBy(
      UserTest.SANJIGI);

  @DisplayName("질문의 작성자가 본인인지 검증.")
  @Test
  void ownerValidation() {
    assertThat(Q1.isOwner(UserTest.SANJIGI)).isFalse();
    assertThat(Q1.isOwner(UserTest.JAVAJIGI)).isTrue();
  }

  @DisplayName("질문 삭제가 불가능한 경우 검증.")
  @Test
  void removeValidation() {
    assertThatThrownBy(() -> Q1.findDeleteQuestionAndAnswers(UserTest.SANJIGI, ofId(1L).getId()))
        .isInstanceOf(CannotDeleteException.class);
  }

  @DisplayName("질문삭제 대상 콜랙션을 생성.")
  @Test
  void create() throws CannotDeleteException {
    List<DeleteHistory> deleteObject = Q1.findDeleteQuestionAndAnswers(
        UserTest.JAVAJIGI, ofId(1L).getId());

    assertThat(deleteObject.size()).isEqualTo(1);

    List<DeleteHistory> otherDeleteObject = Q2.findDeleteQuestionAndAnswers(
        UserTest.SANJIGI, ofId(2L).getId());

    assertThat(deleteObject).isNotEqualTo(otherDeleteObject);
  }

  public static Question ofId(long questionId) {
    return new Question(questionId, "title1", "contents1").writeBy(
        UserTest.JAVAJIGI);
  }
}
