package tqs.homework.cache;

import static org.junit.jupiter.api.Assertions.*;
import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CacheTest {
    private Cache cache;
    private static final long TTL = 5L; 

    @BeforeEach
    void setUp() throws IllegalAccessException {
        cache = new Cache(TTL);
    }

    @Test 
    void whenBadTTL_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {new Cache(-5);});
    }

    @Test
    void addAndExistsTest() {
        String key = "https://fakeurl.com";
        String invalid_key = "some_fake_key";
        String value = "some_response";

        cache.add(key, value);
        assertTrue(cache.exists(key));
        assertFalse(cache.exists(invalid_key));
    }

    @Test
    void getValueTest() {
        String key = "https://fakeurl.com";
        String value = "some_response";

        cache.add(key, value);
        assertEquals(value, cache.getValue(key));
    }

    @Test
    void whenGetNonExistentKey_thenMissesAndRequestsIncrement() {
        String invalid_key = "some_fake_key";

        assertFalse(cache.exists(invalid_key));
        assertEquals(1, cache.getRequests());
        assertEquals(1, cache.getMisses());
        assertEquals(0, cache.getHits());
    }

    @Test
    void whenGetExistentAndNotExperideKey_thenHitsAndRequestsIncrement() {
        String key = "https://fakeurl.com";
        String value = "some_response";

        cache.add(key, value);
        assertTrue(cache.exists(key));
        assertFalse(cache.isExpired(key));
        assertEquals(1, cache.getRequests());
        assertEquals(1, cache.getHits());
        assertEquals(0, cache.getMisses());
    }

    @Test 
    void whenExpiredKey_thenRemove() throws InterruptedException {
        String key = "https://fakeurl.com";
        String value = "some_response";

        cache.add(key, value);
        assertTrue(cache.exists(key));
        Thread.sleep(TTL+10);
        assertTrue(cache.isExpired(key), "ERROR");
        assertFalse(cache.exists(key));
    }

}
