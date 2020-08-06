import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        int actual = this.getLocalNumber();
        int extend = 14;
        Assert.assertTrue("Метод getLocalNumber возвращает " + this.getLocalNumber() + ", а не 14.",actual == extend);
    }
}
