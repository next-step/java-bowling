package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;
    private Answer answer;
    private Answer answer2;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer2 = new Answer(UserTest.JAVAJIGI, question, "answer contents2");
        question.addAnswer(answer);
    }


    @DisplayName("질문자가 본인이 아니면 질문을 삭제할 수 없다.")
    @Test
    public void delete_fail_not_owner() {
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문자 본인이고, 답변이 없으면 삭제가 가능하다.")
    @Test
    public void delete_success_owner_and_no_answer() {
        assertThat(question.isDeleted()).isFalse();

        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문자가 본인이고, 답변이 있는 경우, 모든 답변자가 본인이면 삭제가 가능하다.")
    @Test
    public void delete_success_question_and_answer_owner() {
        question.addAnswer(answer);
        assertThat(question.isDeleted()).isFalse();

        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문자가 본인이고, 답변이 있는 경우, 모든 답변자가 본인이 아니면 삭제가 불가능하다.")
    @Test
    public void delete_fail_answer_other() {
        question.addAnswer(AnswerTest.A1);
        question.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문이 삭제되면 답변도 모두 삭제된다.")
    @Test
    public void delete_success_all_answer_deleted() {
        question.addAnswer(answer2);
        assertAll(
                () -> assertThat(question.isDeleted()).isFalse(),
                () -> assertThat(answer.isDeleted()).isFalse(),
                () -> assertThat(answer2.isDeleted()).isFalse()
        );

        question.delete(UserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(question.isDeleted()).isTrue(),
                () -> assertThat(answer.isDeleted()).isTrue(),
                () -> assertThat(answer2.isDeleted()).isTrue()
        );
    }

}
