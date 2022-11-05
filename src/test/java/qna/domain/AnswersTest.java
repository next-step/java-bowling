package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    private Answer answer1;

    @BeforeEach
    public void setUp() throws Exception {
        answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    public void delete_성공_답변자_같음() throws Exception {
        Answer answer2 = new Answer(12L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        assertThat(answer1.isDeleted()).isFalse();
        assertThat(answer2.isDeleted()).isFalse();

        Answers answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);
        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).hasSize(2);
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
    }

    @Test
    public void delete_실패_다른_사람이_쓴_답변도있는경우() throws Exception {
        Answer answer2 = new Answer(12L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        assertThat(answer1.isDeleted()).isFalse();
        assertThat(answer2.isDeleted()).isFalse();

        Answers answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);

        assertThatThrownBy(() -> {
            answers.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
