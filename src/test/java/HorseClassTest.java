import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseClassTest {

    @Nested
    class ConstructorTests {
        @Test
        void Param1_Null_Exeption() {
            assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5.8, 3.1));
        }

        @Test
        void Param1_Null_ExeptionMessage() {
            try {
                new Horse(null, 8.9, 2);
            } catch (IllegalArgumentException e) {
                assertEquals("Name cannot be null.", e.getMessage());
            }
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   ", "\t", "\n", "\r"})
        void Param1_Empty_Exeption(String str) {
            assertThrows(IllegalArgumentException.class, () -> new Horse(str, 20.1, 2.4));
        }

        @ParameterizedTest
        @ValueSource(strings = {"", "   ", "\t", "\n", "\r"})
        void Param1_Empty_ExeptionMessage(String str) {
            try {
                new Horse(str, 12.3, 2.4);
            } catch (IllegalArgumentException e) {
                assertEquals("Name cannot be blank.", e.getMessage());
            }
        }

        @Test
        void Param2_Negative_Exeption() {
            assertThrows(IllegalArgumentException.class, () -> new Horse("Kkk", -456, 2.5));
        }

        @Test
        void Param2_Negative_ExeptionMessage() {
            try {
                new Horse("Kkk", -23, 2.5);
            } catch (IllegalArgumentException e) {
                assertEquals("Speed cannot be negative.", e.getMessage());
            }
        }

        @Test
        void Param3_Negative_Exeption() {
            assertThrows(IllegalArgumentException.class, () -> new Horse("Kkk", 12.3, -2.7));
        }

        @Test
        void Param3_Negative_ExeptionMessage() {
            try {
                new Horse("Kkk", 12.3, -2.7);
            } catch (IllegalArgumentException e) {
                assertEquals("Distance cannot be negative.", e.getMessage());
            }
        }
    }

    @Nested
    class MethodsTests {

        @Test
        void getName_invocation_test() {
            Horse horsee = new Horse("Loshadka", 5.8, 3.1);
            assertEquals("Loshadka", horsee.getName());
        }

        @Test
        void getSpeed_invocation_test() {
            Horse horsee = new Horse("Loshadka", 5.8, 3.1);
            assertEquals(5.8, horsee.getSpeed());
        }

        @Test
        void getDistance_invocation_test() {
            Horse horsee = new Horse("Loshadka", 6.8, 3.2);
            assertEquals(3.2, horsee.getDistance());
        }

        @Test
        void getDistance_invocationWith2ParamsConstructor_test2() {
            Horse horsee = new Horse("Loshadka", 5.8);
            assertEquals(0, horsee.getDistance());
        }

        @Test
        void move_invocationStatic_test() {
            try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
                new Horse("Loshadka", 5.8).move();
                mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @ParameterizedTest
        @CsvSource({"100.0, 50.0, 0.2, 110.0",    // 100 + 50*0.2 = 110
                    "100.0, 50.0, 0.5, 125.0",    // 100 + 50*0.5 = 125
                    "100.0, 50.0, 0.9, 145.0",    // 100 + 50*0.9 = 145
                    "0.0, 100.0, 0.3, 30.0",      // 0 + 100*0.3 = 30
                    "200.0, 25.0, 0.8, 220.0" })  // 200 + 25*0.8 = 220
        void move_formula_test(double startDistance,
                               double speed,
                               double mockedValue,
                               double resDistance) {
            try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
                mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockedValue);
                Horse horsee = new Horse("Loshadka", speed, startDistance);
                horsee.move();
                assertEquals(resDistance, horsee.getDistance());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
