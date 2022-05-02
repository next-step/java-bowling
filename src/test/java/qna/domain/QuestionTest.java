package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("질문")
public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("삭제")
    void delete() throws CannotDeleteException {
        //given
        Question question = new Question("title", "contents")
                .writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "contents");
        question.addAnswer(answer);
        //when
        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);
        //then
        assertAll(
                () -> assertThat(question.isDeleted()).isTrue(),
                () -> assertThat(answer.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories).hasSize(2)
        );
    }

    @Test
    @DisplayName("본인의 질문이 아니라면 삭제 불가능")
    void delete_notOwner_thrownCannotDeleteException() {
        //given
        Question question = new Question("title", "contents").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "contents");
        question.addAnswer(answer);
        //when, then
        assertAll(
                () -> assertThatExceptionOfType(CannotDeleteException.class)
                        .isThrownBy(() -> question.delete(UserTest.SANJIGI))
                        .withMessageContaining("질문을 삭제할 권한이 없습니다."),
                () -> assertThat(question.isDeleted()).isFalse(),
                () -> assertThat(answer.isDeleted()).isFalse()
        );
    }

    @Test
    @DisplayName("다른 사람의 답변이 있다면 삭제 불가능")
    void delete_containsNotOwnerAnswer_thrownCannotDeleteException() {
        //given
        Question question = new Question("title", "contents").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.SANJIGI, question, "contents");
        question.addAnswer(answer);
        //when, then
        assertAll(
                () -> assertThatExceptionOfType(CannotDeleteException.class)
                        .isThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                        .withMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."),
                () -> assertThat(question.isDeleted()).isFalse(),
                () -> assertThat(answer.isDeleted()).isFalse()
        );
    }
}
