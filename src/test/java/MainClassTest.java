import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainClassTest {
    @Disabled
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void timeout_test(){
        try {
            Main.main(new String[]{});
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
