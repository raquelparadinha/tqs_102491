package tqs.lab2.connection;

import java.io.IOException;

/**
 *
 * @author ico
 */
public interface ISimpleHttpClient {
    
    public String doHttpGet(String url) throws IOException;
}

