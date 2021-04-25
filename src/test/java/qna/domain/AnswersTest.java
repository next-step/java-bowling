package qna.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AnswersTest {
    private Answer javajigiAnswer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private Answer javajigiAnswer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1-1");
    private Answer sanjigiAnswer = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void When_New_Then_Created() {
        assertDoesNotThrow((Executable) Answers::new);
    }

    @Test
    void When_Add_Then_Added() {
        Answers expect = new Answers();
        expect.add(new Answer());

        Answers actual = new Answers();
        actual.add(new Answer());

        assertThat(actual).isEqualTo(expect);
    }

    @Test
    void When_Delete_Then_AnswerIsDeleted() {
        Answers answers = new Answers();
        answers.add(javajigiAnswer);

        answers.delete(UserTest.JAVAJIGI);

        assertThat(javajigiAnswer.isDeleted()).isTrue();
    }

    @Test
    void Given_AnswersWithTwoWriter_When_Delete_BothAreNotDeleted() throws IllegalArgumentException {
        Answers answers = new Answers();
        answers.add(javajigiAnswer);
        answers.add(sanjigiAnswer);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> answers.delete(UserTest.JAVAJIGI));
        assertThat(javajigiAnswer.isDeleted()).isFalse();
        assertThat(sanjigiAnswer.isDeleted()).isFalse();
    }

    @Test
    void When_Delete_Then_ReturnsDeleteHistories() {
        Answers answers = new Answers();
        answers.add(javajigiAnswer);
        answers.add(javajigiAnswer2);

        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories).contains(new DeleteHistory(ContentType.ANSWER, javajigiAnswer.getId(), javajigiAnswer.getWriter(), LocalDateTime.now()));
        assertThat(deleteHistories).contains(new DeleteHistory(ContentType.ANSWER, javajigiAnswer2.getId(), javajigiAnswer2.getWriter(), LocalDateTime.now()));
    }
}
