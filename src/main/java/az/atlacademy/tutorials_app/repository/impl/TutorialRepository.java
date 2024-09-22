package az.atlacademy.tutorials_app.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import az.atlacademy.tutorials_app.model.entity.TutorialEntity;
import az.atlacademy.tutorials_app.repository.MyRepository;

@Repository
public class TutorialRepository implements MyRepository<TutorialEntity, Integer> 
{
    private final JdbcTemplate jdbcTemplate;

    public TutorialRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;

        String sql = """
            CREATE TABLE IF NOT EXISTS tutorials (
                id SERIAL PRIMARY KEY,
                title VARCHAR(255) NOT NULL,
                description TEXT,
                published BOOLEAN NOT NULL
            );
        """;

        jdbcTemplate.execute(sql);
        
    }

    @Override
    public TutorialEntity findById(Integer id)
    {
        try
        {
            String query = "SELECT * FROM tutorials WHERE id = ?";
            TutorialEntity entity = jdbcTemplate.query(query, (rs, rowNum) -> {
                return TutorialEntity.builder()
                    .id(rs.getInt("id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .published(rs.getBoolean("published"))
                    .build();
                }, id
            ).get(0);
            return entity; 
        }
        catch(IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    @Override
    public TutorialEntity save(TutorialEntity tutorialEntity)
    {
        if(tutorialEntity.getId() == 0)
        {
            String sql = "INSERT INTO tutorials (title, description, published) VALUES (?,?,?) RETURNING id";
            tutorialEntity.setId(
                jdbcTemplate.queryForObject(
                    sql, 
                    Integer.class, 
                    tutorialEntity.getTitle(), 
                    tutorialEntity.getDescription(), 
                    tutorialEntity.isPublished()
                )
            );
        }
        else
        {
            String sql = "UPDATE tutorials SET title=?, description=?, published=? WHERE id=?";
            jdbcTemplate.update(
                sql, 
                tutorialEntity.getTitle(), 
                tutorialEntity.getDescription(), 
                tutorialEntity.isPublished(), 
                tutorialEntity.getId()
            );
        }

        return tutorialEntity;
    }

    @Override
    public List<TutorialEntity> findAll()
    {
        String query = "SELECT * FROM tutorials";
        List<TutorialEntity> entities = jdbcTemplate.query(query, (rs, rowNum) -> {
            TutorialEntity entity = new TutorialEntity();
            entity.setId(rs.getInt("id"));
            entity.setTitle(rs.getString("title"));
            entity.setDescription(rs.getString("description"));
            entity.setPublished(rs.getBoolean("published"));
            return entity;
        });
        return entities;
    }

    @Override
    public void deleteById(Integer id)
    {
        String sql = "DELETE FROM tutorials WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override 
    public void deleteAll()
    {
        String sql = "DELETE FROM tutorials";
        jdbcTemplate.update(sql);
    }

    public List<TutorialEntity> findByPublished(boolean published)
    {

        String query = "SELECT * FROM tutorials WHERE published = ?";

        List<TutorialEntity> entities = jdbcTemplate.query(
            query, 
            (rs, rowNum) -> {
                TutorialEntity entity = new TutorialEntity();
                entity.setId(rs.getInt("id"));
                entity.setTitle(rs.getString("title"));
                entity.setDescription(rs.getString("description"));
                entity.setPublished(rs.getBoolean("published"));
                return entity;
            }, 
            published
        );

        return entities;
    }
}
