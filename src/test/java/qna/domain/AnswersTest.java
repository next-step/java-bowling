package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AnswersTest {
    private Answer answer;

    @BeforeEach
    void setup() {
        User user = User.create(1L, "aiden", "password", "심준보", "s860620@gmail.com");
        Question question = Question.create(1L, "제목", "본문");
        answer = Answer.create(1L, user, question, "본문");
    }

    @Test
    @DisplayName("답변 추가 검증")
    void addAnswer() {
        Answers answers = Answers.create();

        assertDoesNotThrow(
                () -> answers.addAnswer(answer));
    }
}
