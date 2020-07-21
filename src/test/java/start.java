import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;


public class start {

    public static WebDriver driver;
    public static boolean checkResult=false;
    public static String city;

    @Пусть("открыт ресурс авито")
    public static void open() {
        System.setProperty("webdriver.chrome.driver", "D:\\IntelliJ IDEA Community Edition 2020.1.2\\homework\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.avito.ru/izhevsk");
    }

    @ParameterType(".*")
    public Categories category(String type) {
        return Categories.valueOf(type);
    }
    @И("в выпадающем списке категорий выбрана {category}")
    public void orgteh(Categories category){
    Select genreSelect = new Select(driver.findElement(By.xpath("//select[@class='select-select-3CHiM']")));
    genreSelect.selectByVisibleText(category.type);
}


    @И("в поле поиска введено значение {string}")
    public void printer(String string){
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys(string);
    }

    @Тогда("кликнуть по выпадающему списку региона")
    public void region(){
        driver.findElement(By.xpath("//div[@class='main-select-2pf7p main-location-3j9by']")).click();
    }

    @Тогда("в поле регион введено значение {string}")
    public void Vladivostok(String string){
        driver.findElement(By.xpath("//input[@class='suggest-input-3p8yi']")).sendKeys(string);
        city=string;
        try {
            Thread.sleep(1000);// если не подождать то он ижевск ставит
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @И("нажата кнопка показать объявления")
    public void PressButton(){
        driver.findElement(By.xpath("//li[@class='suggest-suggest-1wwEm text-text-1PdBw text-size-m-4mxHN']")).click();
        driver.findElement(By.xpath("//button[@class='button-button-2Fo5k button-size-m-7jtw4 button-primary-1RhOG']")).click();
    }
    @Тогда("открылась страница результаты по запросу {string}")
    public void wtf(String string){
        WebElement check = driver.findElement(By.xpath("//h1[@class='page-title-text-WxwN3 page-title-inline-2v2CW']"));
        if (check.getText().contains(string) && check.getText().contains(city)) {
            checkResult = true;
        }
        assertTrue(checkResult, "Не открылась нужная страница");
    }
    @И("активирован чекбокс только с фотографией")
    public void checkbox(){
        WebElement Checkbox = driver.findElement(By.xpath("//label[@class='checkbox-checkbox-7igZ6 checkbox-size-s-yHrZq']"));
        if (!Checkbox.isSelected()) {
            Checkbox.click();
        }
    }

    @ParameterType(".*")
    public PriceType price(String type) {
        return PriceType.valueOf(type);
    }
    @И("в выпадающем списке сортировка выбрано значение {price}")
    public void pricecheck(PriceType pricetype){
        driver.findElement(By.xpath("//button[@class='button-button-2Fo5k button-size-s-3-rn6 button-primary-1RhOG width-width-12-2VZLz']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//option[text()='" + pricetype.type + "']")).click();
    }
    @И("в консоль выведено значение названия и цены {int} первых товаров")
    public void out(int arg){
        List<WebElement> Price = driver.findElements(By.xpath("//div[@class='snippet-price-row']"));
        List<WebElement> Title = driver.findElements(By.xpath("//div[@class='snippet-title-row']"));

        for (int i = 1; i <= arg; i++) {
            System.out.print("Принтер " +i+ " - "+ Title.get(i).findElement(By.xpath(".//h3/a[@class='snippet-link']")).getText()+" , ");
            System.out.print("Цена - "+ Price.get(i).findElement(By.xpath(".//span[@class='snippet-price ']")).getText()+" | ");
        }
    }
}