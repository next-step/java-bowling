package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    @DisplayName("answers 삭제하기")
    void deleteBy_writer() throws CannotDeleteException {
        // given
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1 by javajigi");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer2 by javajigi");
        Answers answers =  new Answers(Arrays.asList(answer1, answer2));

        List<DeleteHistory> expected = new ArrayList<>();
        expected.add(answer1.delete());
        expected.add(answer2.delete());

        // when
        List<DeleteHistory> result = answers.deleteBy(UserTest.JAVAJIGI);

        // then
        assertThat(result).isEqualTo(expected);

    }

    @Test
    @DisplayName("answers 삭제하기 실패")
    void deleteBy_other_user() {
        // given
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1 by javajigi");
        Answer answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "answer1 by sanjigi");
        Answers answers =  new Answers(Arrays.asList(answer1, answer2));

        // when
        assertThatThrownBy(() -> { answers.deleteBy(UserTest.JAVAJIGI); })
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

    }

}
