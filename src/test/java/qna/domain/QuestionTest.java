package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("삭제 성공 테스트")
    public void deleteSuccess() throws Exception {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.addQuestionDeleteHistory(Q1.getWriter(), UserTest.JAVAJIGI.getId());
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 실패 다른 사람이 쓴 글 삭제 실패 테스트")
    public void deleteOtherUserWrite(){
        assertThatThrownBy(() -> {
            Q1.isOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 성공 질문자가 같음")
    public void deleteSuccessSameUser() throws Exception {
        Q2.isOwner(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }
}
