package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnswersTest {

    private Answer answer1;
    private Answer answer2;
    
    @BeforeEach
    void setUp(){
        answer1 = AnswerTest.A1;
        answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @DisplayName("답변글 작성자가 모두 같지 않으면 답변을 삭제할 수 없다.")
    @Test
    void delete_all_error() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2)).deleteAnswers(UserTest.JAVAJIGI));
    }

    @DisplayName("답변글 작성자가 모두 같으면 답변을 삭제할 수 있다.")
    @Test
    void delete_all() throws CannotDeleteException {
        assertThat(new Answers(Arrays.asList(answer1, answer2))
                .deleteAnswers(UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistories(Arrays.asList(
                        new DeleteHistory(ContentType.ANSWER, answer1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, answer2.getId(), UserTest.JAVAJIGI, LocalDateTime.now()))));
    }

    @DisplayName("답변글들 삭제에 성공하면 삭제할 정보를 담은 DeleteHistory 객체를 반환한다.")
    @Test
    void delete_deleteHistory() throws CannotDeleteException {
        assertThat(new Answers(Arrays.asList(answer1))
                .deleteAnswers(UserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistories(Arrays.asList(new DeleteHistory(ContentType.ANSWER, answer1.getId(), UserTest.JAVAJIGI, LocalDateTime.now()))));
    }
}