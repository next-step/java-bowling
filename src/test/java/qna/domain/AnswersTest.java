package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {

    @DisplayName("Question의 답변이 작성자 본인이 작성한 답변만 있는 경우는 삭제 유효성 정상")
    @Test
    public void validateAnswerWriters_정상() {
        Question Q3 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, Q3, "질문 작성자가 답변 달았음.");
        Q3.addAnswer(answer);

        Answers answers = Q3.getAnswers();

        assertThatCode(() -> {
            answers.delete2(UserTest.JAVAJIGI, ContentType.ANSWER, LocalDateTime.now());
        }).doesNotThrowAnyException();
    }

    @DisplayName("Question의 답변 중 작성자가 쓰지 않은 답변이 있는 경우 삭제 유효성 예외 발생")
    @Test
    public void validateAnswerWriters_에러() {
        Question Q3 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.SANJIGI, Q3, "질문 작성자가 아닌 사람이 답변 달았음.");
        Q3.addAnswer(answer);

        Answers answers = Q3.getAnswers();

        assertThatThrownBy(() -> {
            answers.delete2(UserTest.JAVAJIGI, ContentType.ANSWER, LocalDateTime.now());
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("Question의 답변이 없는 경우에도 삭제 유효성 통과 및 정상 삭제")
    @Test
    public void validateAnswerWriters_답변없음_정상() {
        assertThatCode(() -> {
            QuestionTest.Q2.deleteQnA(UserTest.SANJIGI, LocalDateTime.now());
        }).doesNotThrowAnyException();
    }

    @DisplayName("Question 객체에게 지니고 있는 Answer들의 삭제를 요청하면 정상 수행")
    @Test
    public void delete_answer_true() throws CannotDeleteException {
        Question Q3 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, Q3, "질문 작성자가 답변 달았음.");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, Q3, "질문 작성자가 답변 달았음.");
        Q3.addAnswer(answer);
        Q3.addAnswer(answer2);

        Q3.deleteQnA(UserTest.JAVAJIGI, LocalDateTime.now());

        assertThat(answer.isDeleted()).isEqualTo(true);
        assertThat(answer2.isDeleted()).isEqualTo(true);
    }
}
