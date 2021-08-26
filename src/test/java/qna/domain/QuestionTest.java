package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(MockitoJUnitRunner.class)
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Mock
    private QuestionRepository questionRepository;

    @DisplayName("다른 유저가 작성한 경우 삭제 실패")
    @Test
    public void delete_fail(){
        assertThatThrownBy(()-> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("같은 유저가 작성한 경우 삭제 성공")
    @Test
    public void delete_success() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
