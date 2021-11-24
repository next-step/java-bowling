package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AnswersTest {
    private Answers answers;

    @BeforeEach
    void setup() {
        Answer answer1 = Answer.create(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answer2 = Answer.create(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");

        answers = Answers.create();
        answers.addAnswer(answer1);
        answers.addAnswer(answer2);
    }

    @Test
    @DisplayName("답변 추가 검증")
    void addAnswer() {
        Answer answer2 = Answer.create(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

        assertDoesNotThrow(
                () -> answers.addAnswer(answer2));
    }

    @Test
    @DisplayName("삭제 시, 정상적으로 삭제 이력 반환 검증")
    void delete() {
        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories.size()).isEqualTo(2);
    }
}
