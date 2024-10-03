package az.atlacademy.tutorials_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.atlacademy.tutorials_app.model.entity.TutorialEntity;
import java.util.List;

public interface TutorialRepository extends JpaRepository<TutorialEntity, Long>
{
    List<TutorialEntity> findByPublished(boolean published);
}
