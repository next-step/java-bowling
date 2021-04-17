package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

public class AnswersTest {
    private Answers answers;

    @BeforeEach
    void setUp() {
        answers = new Answers(new ArrayList<>(Arrays.asList(AnswerTest.A1, AnswerTest.A1)));
    }

    @Test
    @DisplayName("인자로 받은 로그인 사용자가 모든 답변의 작성자가 아닐 경우 예외가 발생한다.")
    public void checkWriters() throws Exception {
        assertThatThrownBy(() -> answers.checkWriters(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("인자로 받은 로그인 사용자가 모든 답변의 작성자일 경우 예외가 발생하지 않는다.")
    public void checkWriters_allSameWriters() throws Exception {
        answers.checkWriters(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("답변이 존재하지 않는 경우 예외가 발생하지 않는다.")
    public void checkWriters_noneAnswers() throws Exception {
        //given
        Answers answers = new Answers();

        //when
        answers.checkWriters(UserTest.SANJIGI);
        answers.checkWriters(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("답변을 인자로 받아 답변 목록에 추가한다.")
    public void add() throws Exception {
        //when
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "add"));

        then(answers.list()).hasSize(3);
    }
}
