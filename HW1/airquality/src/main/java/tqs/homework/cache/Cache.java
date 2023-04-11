package tqs.homework.cache;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Cache {
    private Map<String, CacheItem> cacheMap = new HashMap<String, CacheItem>();
    private long ttl;
    private int requests = 0, hits = 0, misses = 0;

    public Cache() {
        this.ttl = 60*10000L;
    }

    public Cache(long ttl) {
        if (ttl <= 0) throw new IllegalArgumentException("Time To Live value must be greater than 0.");
        this.ttl = ttl;
    }

    public Boolean exists(String key) {
        this.requests++;
        
        if (this.cacheMap.containsKey(key)) return true;
        
        this.misses++;
        return false;
    }

    public Boolean isExpired(String key) {
        Long ts = this.cacheMap.get(key).ts;
        if (ts < System.currentTimeMillis()) {
            this.cacheMap.remove(key);
            return true;
        }
        this.hits++;
        return false;
    }

    public String getValue(String key) {
        String value = this.cacheMap.get(key).value;
        this.cacheMap.get(key).renewTS(this.ttl);
        return value;
    }

    public void add(String key, String value) {
        CacheItem item = new CacheItem(this.ttl, value);
        cacheMap.put(key, item);
    }

    public Map<String, Integer> getStats() {
        Map<String, Integer> stats = new HashMap<String, Integer>();

        stats.put("Requests", this.requests);
        stats.put("Hits", this.hits);
        stats.put("Misses", this.misses);

        return stats;
    }

    protected class CacheItem {
        private Long ts;
        private String value;
    
        public CacheItem(Long ttl, String value) {
            this.ts = System.currentTimeMillis() + ttl;
            this.value = value;
        }

        public void renewTS(Long ttl) {
            this.ts = System.currentTimeMillis() + ttl;
        }
    }
}



