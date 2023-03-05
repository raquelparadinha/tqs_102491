package tqs.lab2.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tqs.lab2.connection.TqsBasicHttpClient;
import tqs.lab2.geocoding.Address;
import tqs.lab2.geocoding.AddressResolver;

public class AddressResolverIT {

    AddressResolver resolver;

    @BeforeEach
    void startUp() {
        resolver = new AddressResolver(new TqsBasicHttpClient());
    }

    @Test
    void whenResolveDetiGps_returnJacintoMagalhaeAddress() throws ParseException, IOException, URISyntaxException, java.text.ParseException {

        Optional<Address> result = resolver.findAddressForLocation( 40.633116,-8.658784);

        Address expected = new Address( "Avenida João Jacinto de Magalhães", "Aveiro", "", "3810-149", null);

        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException, java.text.ParseException {

        assertThrows(IllegalArgumentException.class, () -> resolver.findAddressForLocation( -361,-361));

    }
    
}
