package tqs.lab2;

// import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class StocksPortfolioTest {

    @Mock
    IStockMarketService market;
    
    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    public void testGetTotalValue() {

        when(market.lookUpPrice("AMZ")).thenReturn(2.0);
        when(market.lookUpPrice("APP")).thenReturn(3.0);

        portfolio.addStock(new Stock("AMZ", 4));
        portfolio.addStock(new Stock("APP", 2));

        // assertEquals(14, portfolio.getTotalValue());

        // assert with Hamcrest
        assertThat(14.0, equalTo(portfolio.getTotalValue()));

    }
}
