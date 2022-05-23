package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    public void setUp() {
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answers = new Answers(List.of(answer));
    }

    @Test
    @DisplayName("다른 사람이 쓴 글일 경우 CannotDeleteException 반환한다.")
    public void invalidWriter() {
        assertThatThrownBy(() -> answers.deleteAll(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}