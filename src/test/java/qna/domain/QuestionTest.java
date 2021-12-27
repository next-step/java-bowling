package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("답글이 없는 글을 삭제한다.")
    @Test
    void deleteQuestion() {
        Question question = Q2;

        assertThat(question.isDeleted()).isFalse();
        question.delete(UserTest.SANJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("자신이 작성한 질문만 삭제할 수 있다.")
    @Test
    void deleteQuestionFailNotAuthority() {
        Question question = Q2;

        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문자와 답변자가 모두 같아야 삭제 할 수 있다.")
    @Test
    void deleteQuestionAndAnswer() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answerOne = new Answer(UserTest.JAVAJIGI, question, "answer one");
        Answer answerTwo = new Answer(UserTest.JAVAJIGI, question, "answer two");

        question.addAnswer(answerOne);
        question.addAnswer(answerTwo);

        assertThat(question.isDeleted()).isFalse();
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 답변자 중 하나라도 다르면 삭제가 안된다.")
    @Test
    void deleteQuestionAndAnswerFailDifferentUser() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answerOne = new Answer(UserTest.JAVAJIGI, question, "answer one");
        Answer answerTwo = new Answer(UserTest.SANJIGI, question, "answer two");

        question.addAnswer(answerOne);
        question.addAnswer(answerTwo);

        assertThat(question.isDeleted()).isFalse();
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        assertThat(question.isDeleted()).isTrue();
    }
}
