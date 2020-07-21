import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class avito {


    public static void main(String[] args) {
        //Часть 1

        //Установка пути  до драйвера
        System.setProperty("webdriver.chrome.driver", "D:\\IntelliJ IDEA Community Edition 2020.1.2\\homework\\chromedriver.exe");
        //Создание экземпляра драйвера
        WebDriver driver = new ChromeDriver();
        //Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        //Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Загрузка новой страницы в текущем окне браузера
        driver.get("https://www.avito.ru/izhevsk");

        //Найдем элемент по селектору
        Select genreSelect = new Select(driver.findElement(By.xpath("//select[@class='select-select-3CHiM']")));
        genreSelect.selectByVisibleText("Оргтехника и расходники");

        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Принтер");

        driver.findElement(By.xpath("//div[@class='main-select-2pf7p main-location-3j9by']")).click();

        driver.findElement(By.xpath("//input[@class='suggest-input-3p8yi']")).sendKeys("Владивосток");

        try {
            Thread.sleep(1000);// если не подождать то он ижевск ставит
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//li[@class='suggest-suggest-1wwEm text-text-1PdBw text-size-m-4mxHN']")).click();

        driver.findElement(By.xpath("//button[@class='button-button-2Fo5k button-size-m-7jtw4 button-primary-1RhOG']")).click();

        WebElement Checkbox = driver.findElement(By.xpath("//label[@data-marker='delivery-filter']"));
        if (!Checkbox.isSelected()) {
            Checkbox.click();
        }

        driver.findElement(By.xpath("//button[@class='button-button-2Fo5k button-size-s-3-rn6 button-primary-1RhOG width-width-12-2VZLz']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//option[text()='Дороже']")).click();

        List<WebElement> Price = driver.findElements(By.xpath("//div[@class='snippet-price-row']"));
        List<WebElement> Title = driver.findElements(By.xpath("//div[@class='snippet-title-row']"));

        for (int i = 1; i <= 3; i++) {
            System.out.print("Принтер " +i+ " - "+ Title.get(i).findElement(By.xpath(".//h3/a[@class='snippet-link']")).getText()+" , ");
            System.out.print("Цена - "+ Price.get(i).findElement(By.xpath(".//span[@class='snippet-price ']")).getText()+" | ");
        }
    }
}
