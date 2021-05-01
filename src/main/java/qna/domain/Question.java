package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @Embedded
    @AssociationOverride(name = "writer", joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer")))
    private PostInfo postInfo;

    @Embedded
    private Answers answers = new Answers();

    public Question() {
    }

    public Question(String title, String contents) {
        this(null, title, contents);
    }

    public Question(Long id, String title, String contents) {
        super(id);
        this.title = title;
        postInfo = new PostInfo(contents);
    }

    public User getWriter() {
        return postInfo.getWriter();
    }

    public Question writeBy(User loginUser) {
        postInfo.writeBy(loginUser);
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public void validOwner(User loginUser) throws CannotDeleteException {
        if(!postInfo.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        answers.areOwner(loginUser);
    }

    public boolean isDeleted() {
        return postInfo.isDeleted();
    }

    @Override
    public ContentType getContentType() {
        return ContentType.QUESTION;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", postInfo=" + postInfo + "]";
    }

    public List<DeleteHistory> delete() {
        List<DeleteHistory> deleteList = new ArrayList<>();
        postInfo.delete();
        deleteList.add(new DeleteHistory(this, postInfo.getWriter()));
        deleteList.addAll(answers.deleteAll());
        return deleteList;
    }

}
