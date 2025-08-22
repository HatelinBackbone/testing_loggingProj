import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeClassTest {

    @Nested
    class ConstructorTests {
        @Test
        void nullParamException(){
            assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        }

        @Test
        void nullParamExceptionMessage(){
            try{
                new Hippodrome(null);
            }catch (IllegalArgumentException e){
                assertEquals("Horses cannot be null.", e.getMessage());
            }
        }

        @Test
        void emptyParamException(){
            assertThrows(IllegalArgumentException.class, () ->{
                new Hippodrome(new ArrayList<>());
            });
        }

        @Test
        void emptyParamExceptionMessage(){
            try{
                new Hippodrome(new ArrayList<>());
            }catch (IllegalArgumentException e){
                assertEquals("Horses cannot be empty.", e.getMessage());
            }
        }
    }

    @Nested
    class MethodsTests {

        @Test
        void getHorses_invocation_test(){
            ArrayList<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                horses.add(new Horse("Name" + i, i));
            }
            Hippodrome hippodrome = new Hippodrome(horses);
            assertEquals(horses, hippodrome.getHorses());
        }

        @Test
        void move_invocation_test(){
            ArrayList<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                horses.add(Mockito.mock(Horse.class));
            }

            Hippodrome hippodrome = new Hippodrome(horses);
            hippodrome.move();

            for (Horse horse : horses) {
                verify(horse).move();
            }
        }

        @Test
        void getWinner_invocation_test(){
            ArrayList<Horse> horses = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                horses.add(new Horse("Name" + i, 1, i));
            }
            Hippodrome hippodrome = new Hippodrome(horses);

            assertEquals(horses.get(3), hippodrome.getWinner());
        }
    }
}
