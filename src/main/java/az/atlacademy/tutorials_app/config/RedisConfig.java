package az.atlacademy.tutorials_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import az.atlacademy.tutorials_app.model.dto.TutorialResponseDTO;

@Configuration
public class RedisConfig 
{
    @Bean
    public RedisTemplate<Long, TutorialResponseDTO> redisTemplateForTutorials(
        RedisConnectionFactory redisConnectionFactory
    ){
        RedisTemplate<Long, TutorialResponseDTO> redisTemplate = new RedisTemplate<Long, TutorialResponseDTO>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
