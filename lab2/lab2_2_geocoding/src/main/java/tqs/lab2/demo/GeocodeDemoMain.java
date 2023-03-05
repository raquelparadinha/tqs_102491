package tqs.lab2.demo;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URISyntaxException;
import java.util.Optional;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tqs.lab2.connection.TqsBasicHttpClient;
import tqs.lab2.geocoding.Address;
import tqs.lab2.geocoding.AddressResolver;

/**
 *
 * @author ico
 */
public class GeocodeDemoMain {


    static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * demo for address resolver
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws java.text.ParseException {


        try {
            AddressResolver resolver =new AddressResolver( new TqsBasicHttpClient());
            
            Optional<Address> result = resolver.findAddressForLocation( 40.633116,-8.658784);
            log.info("Result: ".concat( result.get().toString() ) );

        } catch (URISyntaxException | IOException | ParseException | org.json.simple.parser.ParseException ex) {
            log.error(String.valueOf(ex));
        }
    }
    
}
