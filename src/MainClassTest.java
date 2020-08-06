import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {
        int actual = this.getLocalNumber();
        int expected = 14;
        Assert.assertTrue("Метод getLocalNumber возвращает " + this.getLocalNumber() + ", а не 14.",actual == expected);
    }

    @Test
    public void  testGetClassNumber() {
        int class_number = this.getClassNumber();
        System.out.println(class_number);
        Assert.assertTrue("Метод getClassNumber возвращает число которое не больше 45",class_number > 45);
    }

}
