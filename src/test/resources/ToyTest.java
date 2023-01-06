import static com.wlnirvana.jtest.JAssertions.*;
import com.wlnirvana.jtest.JTest;

/**
 * An imagninary test file based on your JTest framework, written by a downstream dev
 *
 * NOTE: YOU SHOULD NOT MODIFY THIS FILE
 */
@SuppressWarnings("ALL")
public class ToyTest {

    @JTest
    public static void shouldPass() {
        var s1 = new String("hello");
        var s2 = new String("hello");
        assertEquals(s1, s2);
    }

    @JTest
    public static void assertTrueFail() {
        assertTrue(1 > 2);
    }

    @JTest
    public static void shouldError() {
        int a = 9;
        int b = a / 0;
    }

//    @JTest
//    public static void assertThrowsExceptionPass() {
//        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
//            int[] arr = new int[3];
//            arr[5] = 99;
//        });
//    }
//
//    @JTest
//    public static void assertThrowsExceptionFail() {
//        assertThrows(ArithmeticException.class, () -> {
//            int[] arr = new int[3];
//            arr[5] = 99;
//        });
//    }

}