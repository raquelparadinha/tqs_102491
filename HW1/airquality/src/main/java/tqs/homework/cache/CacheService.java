package tqs.homework.cache;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private Cache cache = new Cache();
    private Logger logger = Logger.getLogger(CacheService.class.getName());

    public Optional<String> get(String key) {
        Optional<String> value = Optional.empty();

        logger.log(Level.INFO, "[CACHE] Looking for key.");
        if (cache.exists(key)) {
            logger.log(Level.INFO, "[CACHE] Key founded.");
            if (!cache.isExpired(key)) {
                value = Optional.of(cache.getValue(key));
                logger.log(Level.INFO, "[CACHE] Key ttl renewed.");
            } else {
                logger.log(Level.INFO, "[CACHE] Removing expired key.");
            }
        } else 
            logger.log(Level.INFO, "[CACHE] Key not found.");

        return value;
    }

    public void put(String key, String value) {
        logger.log(Level.INFO, "[CACHE] Adding new key.");
        cache.add(key, value);
    }

    public Map<String, Integer> cacheStats() {
        return cache.getStats();
    }
}
