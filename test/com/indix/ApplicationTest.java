package com.indix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {
    @Test
    public void shouldCalculateMinimumNumberOfClicksForTestCase1() {
        Application application = new Application();
        application.addLines("1 20", "2 18 19", "5 15 14 17 1 17");
        assertEquals(7, application.calculateMinimumClicksRequired());
    }

    @Test
    public void shouldCalculateMinimumNumberOfClicksForTestCase2() {
        Application application = new Application();
        application.addLines("103 108", "1 104", "5 105 106 107 103 105");
        assertEquals(8, application.calculateMinimumClicksRequired());
    }

    @Test
    public void shouldCalculateMinimumNumberOfClicksForTestCase3() {
        Application application = new Application();
        application.addLines("1 100", "4 78 79 80 3", "8 10 13 13 100 99 98 77 81");
        assertEquals(12, application.calculateMinimumClicksRequired());
    }

    @Test
    public void shouldCalculateMinimumNumberOfClicksForTestCase4() {
        Application application = new Application();
        application.addLines("1 200", "0", "4 1 100 1 101");
        assertEquals(7, application.calculateMinimumClicksRequired());
    }
}
