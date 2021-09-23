package qna.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    static Answers answers = new Answers();

    @Mock
    QuestionRepository repository;

    @Test
    @BeforeAll
    static void 유저저장테스트(){
        answers.add(AnswerTest.A1);
        assertThat(answers.size()).isEqualTo(1);
    }

    @Test
    void 다른사람이글삭제함() throws CannotDeleteException {
        answers.add(AnswerTest.A2);
        assertThatThrownBy(()->{
            answers.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

}