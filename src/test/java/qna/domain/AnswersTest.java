package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static qna.domain.AnswerTest.A1;

public class AnswersTest {

    Answers answers;

    @Before
    public void setUp() throws Exception {
        answers = new Answers();
    }

    @Test
    public void testAddAnswer() {
        answers.add(new Answer());

        assertThat(answers.size()).isEqualTo(1);
    }

    @Test
    public void testDeleteAll() {
        answers.add(new Answer());
        answers.add(new Answer());

        List<DeleteHistory> deleteHistories = answers.deleteAll();

        assertThat(deleteHistories.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("다른 유저의 답변이 존재 할 때 예외 발생")
    public void throwExceptionWhenThereAreOtherWriterAnswer() {
        answers.add(A1);

        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            answers.validate(UserTest.SANJIGI);
        }).withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
