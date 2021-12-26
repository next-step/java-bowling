package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("delete_다른_사람이_쓴_글")
    public void delete_other_writer() throws Exception {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Assertions.assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("delete_성공_질문자_답변자_같음")
    public void delete_success_same_user() throws Exception {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.delete(UserTest.JAVAJIGI, question.getId());
        Assertions.assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("delete_답변_중_다른_사람이_쓴_글")
    public void delete_answers_other_writer() throws Exception {
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Assertions.assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }
}
