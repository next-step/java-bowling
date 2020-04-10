package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("다른 사람이 쓴 글은 삭제 할 수 없다.")
    public void deleteOwnerTest() {
        assertThatThrownBy(() -> Q1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .withFailMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문 답변 중에 다른 사람이 쓴 답변이 있다면 삭제 할 수 없다")
    public void deleteAnswerTest() {
        Question question = new Question().writeBy(UserTest.JAVAJIGI);
        question.addAnswer(AnswerTest.A1);
        question.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> question.deleteBy(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .withFailMessage("다른 사람이 쓴 답변은 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문 삭제 시 질문과 질문 작성자의 답변들도 삭제가 되어야 한다.")
    public void deleteAnswerWhenDeleteQuestionTest() throws Exception {
        User writer = UserTest.JAVAJIGI;
        Question question = new Question().writeBy(writer);
        Answer answer = new Answer(writer, question, "answer1");
        question.addAnswer(answer);

        question.deleteBy(writer);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 삭제 시 질문과 답글 삭제 내역이 리턴되어야 한다")
    public void deleteHistoryTest() throws Exception {
        User writer = UserTest.JAVAJIGI;
        Question question = new Question(1L, "test", "test").writeBy(writer);
        Answer answer = new Answer(1L, writer, question, "answer1");

        question.addAnswer(answer);

        DeleteHistories deleteHistories = question.deleteBy(writer);

        assertThat(deleteHistories.getHistories()).containsExactly(
                new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, 1L, writer, LocalDateTime.now())
        );
    }
}

