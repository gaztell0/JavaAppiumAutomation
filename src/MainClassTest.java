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
        Assert.assertTrue("Метод getClassNumber возвращает число которое не больше 45",class_number > 45);
    }

    @Test
    public void testGetClassString() {
        String class_string = this.getClassString();
        Assert.assertTrue("метод getClassString возвращает строку, в которой нет ни одной из подстрок hello или Hello",class_string.contains("hello") | class_string.contains("Hello"));
    }
}
