package qna.domain;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class UpdatedEntity extends CreatedEntity {
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public UpdatedEntity() {
    }

    public UpdatedEntity(Long id) {
        super(id);
    }

    protected LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
