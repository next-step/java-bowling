package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostInfoTest {
    public static final PostInfo PINF1 = new PostInfo("글내용1", UserTest.JAVAJIGI);
    public static final PostInfo PINF2 = new PostInfo("글내용2", UserTest.SANJIGI);

    @Test
    void 글쓴이확인() {
        assertThat(PINF1.isOwner(UserTest.JAVAJIGI)).isTrue();
        assertThat(PINF1.isOwner(UserTest.SANJIGI)).isFalse();
        assertThat(PINF2.isOwner(UserTest.SANJIGI)).isTrue();
        assertThat(PINF2.isOwner(UserTest.JAVAJIGI)).isFalse();
    }

    @Test
    void 글을삭제한다() {
        assertThat(PINF1.isDeleted()).isFalse();
        PINF1.delete();
        assertThat(PINF1.isDeleted()).isTrue();
    }

}
