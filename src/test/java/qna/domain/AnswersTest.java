package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

class AnswersTest {

    private Answer answer1;
    private Answer answer2;
    private Answers answers;

    @BeforeEach
    void setUp() {
        answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        answers.delete(UserTest.JAVAJIGI);
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() ->
                answers.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_답변_중_다른_사람이_쓴_글() {
        answers.add(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents3"));
        assertThatThrownBy(() ->
            answers.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
