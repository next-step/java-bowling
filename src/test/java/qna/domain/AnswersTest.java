package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    private List<Answer> answers;

    @BeforeEach
    void setUp() {
        answers = new ArrayList<>();

        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3"));
    }

    @DisplayName("전체 Answer를 제거하고 DeleteHistory 리스트를 반환한다.")
    @Test
    void deleteAll() throws CannotDeleteException {
        Answers q1Answers = new Answers(answers);

        List<DeleteHistory> deleteAnswerHistories = q1Answers.deleteAll(UserTest.JAVAJIGI);

        assertThat(deleteAnswerHistories.size()).isEqualTo(answers.size());
    }

    @DisplayName("전체 Answer를 지울때 다른 사람이 쓴 답변이 하나라도 존재하면 CannotDeleteException throw")
    @Test
    void deleteAllThrowException() {
        answers.add(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents4"));
        Answers q1Answers = new Answers(answers);

        assertThatThrownBy(() -> q1Answers.deleteAll(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 존재해서 삭제할 수 없습니다.");
    }
}