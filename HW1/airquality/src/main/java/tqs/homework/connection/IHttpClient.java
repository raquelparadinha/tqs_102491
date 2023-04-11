package tqs.homework.connection;

import java.io.IOException;

public interface IHttpClient {
    public String get(String url, String key) throws IOException;
}
