package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문자가 본인이 아니면 질문을 삭제할 수 없다.")
    @Test
    public void delete_fail_not_owner() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문자 본인이고, 답변이 없으면 삭제가 가능하다.")
    @Test
    public void delete_success_owner_and_no_answer() {
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문자가 본인이고, 답변이 있는 경우, 모든 답변자가 본인이면 삭제가 가능하다.")
    @Test
    public void delete_success_question_and_answer_owner() {
        Q1.addAnswer(AnswerTest.A1);

        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문자가 본인이고, 답변이 있는 경우, 모든 답변자가 본인이 아니면 삭제가 불가능하다.")
    @Test
    public void delete_fail_answer_other() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문이 삭제되면 답변도 모두 삭제된다.")
    @Test
    public void delete_success_all_answer_deleted() {
        final Answer answer01 = new Answer(UserTest.JAVAJIGI, Q1, "answer contents1");
        final Answer answer02 = new Answer(UserTest.JAVAJIGI, Q1, "answer contents2");
        Q1.addAnswer(answer01);
        Q1.addAnswer(answer02);

        Q1.delete(UserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(answer01.isDeleted()).isTrue(),
                () -> assertThat(answer02.isDeleted()).isTrue()
        );
    }

}
