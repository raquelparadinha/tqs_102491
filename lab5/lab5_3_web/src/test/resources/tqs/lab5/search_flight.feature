Feature: Reserve flight

    Scenario: Book a trip from Paris to Rome
        When I navigate to 'https://blazedemo.com/'
            And I choose a ticket from 'Paris' to 'Rome'
            And I click Find Flights
        Then I see 'Flights from Paris to Rome'

    Scenario: Choose flight
        When I navigate to 'https://blazedemo.com/reserve.php'
            And I choose a flight
        Then I see the message that the flight was reserved 