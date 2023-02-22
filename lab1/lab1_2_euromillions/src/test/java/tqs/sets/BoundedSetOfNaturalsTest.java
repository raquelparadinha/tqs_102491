/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    // @Disabled("TODO revise test logic")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        // setB.add(11);
        // assertTrue(setB.contains(11), "add: added element not found in set.");
        // assertEquals(7, setB.size(), "add: elements count not as expected.");
    }

    @Disabled("TODO revise to test the construction from invalid arrays")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test
    public void testAddToFullSet() {
        int[] elems = new int[]{10, -20, -30};

        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @Test 
    public void testAddRepeatedValue() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(2);

        set.add(2);
        assertThrows(IllegalArgumentException.class, () -> set.add(2));
    }

    @Test
    public void testAddNonNaturalNumber() {
        BoundedSetOfNaturals set = new BoundedSetOfNaturals(1);

        assertThrows(IllegalArgumentException.class, () -> set.add(-2));
    }

    @Test
    public void testContains() {
        BoundedSetOfNaturals elemsA = BoundedSetOfNaturals.fromArray(new int[]{100, 10});

        assertTrue(setB.intersects(elemsA), "intersects: intersection not found");

        BoundedSetOfNaturals elemsB = BoundedSetOfNaturals.fromArray(new int[]{100});

        assertFalse(setB.intersects(elemsB), "intersects: invalid intersection");
    }
}
