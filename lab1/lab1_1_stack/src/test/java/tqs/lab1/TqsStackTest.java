package tqs.lab1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class TqsStackTest {

    TqsStack<String> stack;

    @BeforeEach
    public void setStack() {
        stack = new TqsStack<String>();
    }
    
    @DisplayName("Test to check if the stack is empty on construction.")
    @Test
    public void testNewStackEmpty() {
        assertTrue(stack.isEmpty(), "Stack must be empty!");
        assertEquals(0, stack.size(), "Stack's size must be 0!");
    }

    @DisplayName("Test to check if the stack has size n after n pushes.")
    @Test
    public void testPushToEmptyStack() {
        int pushes = 5;
        for (int i = 0; i < pushes; i++) {
            stack.push("a");
        }

        assertFalse(stack.isEmpty(), "Stack isn't empty!");
        assertEquals(pushes, stack.size(), "Stack's size is incorrect!");
    }

    @DisplayName("Test to check if when one pushes x then pops, the value popped is x and size is decreased by one.")
    @Test
    public void testPushThenPop() {
        String obj = "test";
        
        stack.push(obj);
        int initial_size = stack.size();
        
        assertEquals(obj, stack.pop(), "The popped value isn't the last one pushed!");
        assertEquals(initial_size - 1, stack.size(), "Stack's size is incorrect!");
    }

    @DisplayName("Test to check if when one pushes x then peeks, the value returned is x, but the size stays the same.")
    @Test
    public void testPushThenPeek() {
        String obj = "test";
        
        stack.push(obj);
        int size = stack.size();
        
        assertEquals(obj, stack.peek(), "The peeked value isn't the last one pushed!");
        assertEquals(size, stack.size(), "Stack's size is incorrect!");
    }

    @DisplayName("Test to check if when the size is n, then after n pops, the stack is empty and has a size 0.")
    @Test
    public void testEmptyAfterNPops() {

        stack.push("a");
        stack.push("b");
        stack.push("c");

        assertEquals(3, stack.size(), "Stack's size must be 3!");
        int n = stack.size();

        for (int i = n; i > 0; i--) {
            stack.pop();
        }

        assertTrue(stack.isEmpty(), "Stack must be empty!");
    }

    @DisplayName("Test to check if popping from an empty stack does throw a NoSuchElementException.")
    @Test
    public void testPopOnEmptyStack() {
        assertTrue(stack.isEmpty(), "Stack must be empty!");
        assertThrows(NoSuchElementException.class, () -> stack.pop(), "A NoSuchElementException must be thrown!");
    }

    @DisplayName("Test to check if peeking from an empty stack does throw a NoSuchElementException.")
    @Test
    public void testPeekOnEmptyStack() {
        assertTrue(stack.isEmpty(), "Stack must be empty!");
        assertThrows(NoSuchElementException.class, () -> stack.peek(), "A NoSuchElementException must be thrown!");
    }

    @DisplayName("Test to check if pushing onto a full stack does throw an IllegalStateException.")
    @Test
    public void testPushOnFullStack() {
        stack = new TqsStack<String>(2);

        stack.push("a");
        stack.push("b");

        assertThrows(IllegalStateException.class, () -> stack.push("c"));
    }
}
