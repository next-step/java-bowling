package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.entity.Answer;
import qna.domain.entity.DeleteHistory;
import qna.domain.entity.Question;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    public void setup(){
        question = new Question("title", "contents").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("질문 생성")
    public void questionCreate() {
        String title = "title3";
        String contents = "contents3";

        Question question = new Question(title, contents).writeBy(UserTest.JAVAJIGI);
        assertThat(question.equals(new Question(title, contents).writeBy(UserTest.JAVAJIGI))).isTrue();
    }

    @Test
    @DisplayName("질문 생성자 확인")
    public void questionCreateUser() throws CannotDeleteException {
        assertThat(Q1.checkOwnerUser(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("질문 삭제 권한 없음")
    public void questionDeleteNoAuth() {
        assertThatThrownBy(
                () -> Q1.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class).hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("다른 유저의 답변이 있을 경우")
    public void questionDeleteExistOtherUserAnswer() {

        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(
                () -> Q1.delete(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class).hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }


    @Test
    @DisplayName("질문 삭제")
    public void questionDeleteSameUserAnswer() throws CannotDeleteException {

        question.addAnswer(AnswerTest.A1);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents3"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents4"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents5"));

        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }


    @Test
    @DisplayName("질문 삭제 이력")
    public void questionDelete() throws CannotDeleteException {

        question.addAnswer(AnswerTest.A1);

        List<DeleteHistory> delete = question.delete(UserTest.JAVAJIGI);

        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));

        assertThat(delete.equals(deleteHistories)).isTrue();
    }
}
