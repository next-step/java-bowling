package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    public Answer preparedAnswer;

    @BeforeEach
    void setup() {
        preparedAnswer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @DisplayName("답변 작성자가 아닐경우 CannotDeleteException 발생")
    @Test
    void throwExceptionIfNotQuestionOwner() {
        assertThatThrownBy(() -> A1.validateAnswerOwner(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("삭제 메서드 실행 시 answer 삭제 플래그 true,  DeleteHistories 객체 반환")
    @Test
    void deleteQuestion() throws Exception {
        DeleteHistory deleteResult = preparedAnswer.delete(JAVAJIGI);
        assertThat(preparedAnswer.isDeleted()).isTrue();
        assertThat(deleteResult).isEqualTo(new DeleteHistory(ContentType.ANSWER, preparedAnswer.getId(), JAVAJIGI, LocalDateTime.now()));
    }
}
