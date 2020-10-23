package by.mark.orderservice.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("rawtypes")
@EnableCaching
public class CacheConfig {

    public static final String ORDER_CACHE = "order_cache";
    public static final String USER_CACHE = "user_cache";

    @Bean
    public Caffeine caffeineConfig() {
        return Caffeine
                .newBuilder()
                .maximumSize(500)
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .softValues();
    }

    @SuppressWarnings("unchecked")
    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
