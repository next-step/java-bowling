package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public Answer A1;
    public Answer A2;

    public static Answer createAnswer(User user, Question question) {
        return new Answer(user, question, "Answers Contents");
    }

    @BeforeEach
    void setup() {
        A1 = createAnswer(UserTest.JAVAJIGI, QuestionTest.createQuestion(UserTest.JAVAJIGI));
        A2 = createAnswer(UserTest.SANJIGI, QuestionTest.createQuestion(UserTest.SANJIGI));
    }

    @Test
    @DisplayName("정상 삭제")
    void deletedTest() {
        assertThatCode(() -> A1.deleted(UserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThatCode(() -> A2.deleted(UserTest.SANJIGI)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("작성 유저가 아닐경우 Exception")
    void notAvailableDeletedTest() {
        assertThatThrownBy(() -> A2.deleted(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> A1.deleted(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
