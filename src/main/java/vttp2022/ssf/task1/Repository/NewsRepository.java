package vttp2022.ssf.task1.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepository {

    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> template;
    

    public void save (List<String> usArtList) {
        String name = "userSavedArticles";
        ListOperations<String, String> listOps = template.opsForList();
        listOps.leftPushAll(name, 
            usArtList.stream()
                    .toList());
        }

    public String get (String id) {
        ValueOperations<String, String> valueOps = template.opsForValue();
        return valueOps.get(id);
    }

}
