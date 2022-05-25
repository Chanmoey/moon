package common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Chanmoey
 * @date 2022年05月13日
 */
class AlphabetHelperTest {

    @Test
    void test() {
        // letter
        assertTrue(AlphabetHelper.isLetter('a'));
        assertFalse(AlphabetHelper.isLetter('*'));
        assertFalse(AlphabetHelper.isLetter('*'));

        // literal
        assertTrue(AlphabetHelper.isLiteral('a'));
        assertTrue(AlphabetHelper.isLiteral('_'));
        assertTrue(AlphabetHelper.isLiteral('9'));
        assertFalse(AlphabetHelper.isLiteral('*'));

        // number
        assertTrue(AlphabetHelper.isNumber('6'));
        assertTrue(AlphabetHelper.isNumber('0'));
        assertFalse(AlphabetHelper.isNumber('x'));

        //operator
        assertTrue(AlphabetHelper.isOperator('+'));
        assertTrue(AlphabetHelper.isOperator('*'));
        assertTrue(AlphabetHelper.isOperator('/'));
        assertTrue(AlphabetHelper.isOperator('&'));
        assertFalse(AlphabetHelper.isOperator('a'));
        assertTrue(AlphabetHelper.isOperator('%'));
        assertTrue(AlphabetHelper.isOperator('^'));
        assertTrue(AlphabetHelper.isOperator(','));
    }
}
