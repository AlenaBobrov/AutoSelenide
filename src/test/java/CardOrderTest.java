import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class CardOrderTest {
    String generateDate (int daysToAdd, String pattern){
        return LocalDate.now().plusDays(daysToAdd).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    void shouldRegister() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Казань");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy")).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue("Иванова Анна");
        $("[data-test-id= 'phone'] input").setValue("+79261111111");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id= 'notification']").shouldBe(appear, Duration.ofSeconds(15));

    }
    @Test
    void latinCity() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Kazan");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy")).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue("Иванова Анна");
        $("[data-test-id= 'phone'] input").setValue("+79261111111");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='city']//span[contains(text(), 'Доставка в выбранный город недоступна')]").should(appear);
    }

    @Test
    void emptyCity() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy")).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue("Иванова Анна");
        $("[data-test-id= 'phone'] input").setValue("+79261111111");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='city']//span[contains(text(), 'Поле обязательно для заполнения')]").should(appear);
    }
    @Test
    void latinName() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Казань");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy")).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue("Ivanova Anna");
        $("[data-test-id= 'phone'] input").setValue("+79261111111");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='name']//span[contains(text(), 'Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.')]").should(appear);
    }
    @Test
    void emptyName() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Казань");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy")).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id= 'phone'] input").setValue("+79261111111");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='name']//span[contains(text(), 'Поле обязательно для заполнения')]").should(appear);
    }
    @Test
    void spaceName() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Казань");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy")).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue(" ");
        $("[data-test-id= 'phone'] input").setValue("+79261111111");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='name']//span[contains(text(), 'Поле обязательно для заполнения')]").should(appear);
    }
    @Test
    void emptyPhone() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Казань");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy")).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue("Иванова Анна");
        $("[data-test-id= 'phone'] input").setValue("");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='phone']//span[contains(text(), 'Поле обязательно для заполнения')]").should(appear);
    }
    @Test
    void shortPhone() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Казань");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy")).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue("Иванова Анна");
        $("[data-test-id= 'phone'] input").setValue("+792611111");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='phone']//span[contains(text(), 'Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.')]").should(appear);
    }
    @Test
    void unmarkedCheckBox() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Казань");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id= 'date'] input").setValue(generateDate(3, "dd.MM.yyyy")).sendKeys(Keys.chord(Keys.ESCAPE));
        $("[data-test-id='name'] input").setValue("Иванова Анна");
        $("[data-test-id= 'phone'] input").setValue("+79261111111");
        $$("button").find(exactText("Забронировать")).click();
        $x("//label[@data-test-id='agreement'][contains(@class, 'input_invalid')]").should(appear);
    }
    @Test
    void emptyDate() {
        open("http://localhost:9999");
        $("[data-test-id= 'city'] input").setValue("Казань");
        $("[data-test-id = 'date'] input").sendKeys(Keys.chord(Keys.CONTROL,"a"), BACK_SPACE);
        $("[data-test-id='name'] input").setValue("Иванова Анна");
        $("[data-test-id= 'phone'] input").setValue("+79261111111");
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $x("//span[@data-test-id='date']//span[contains(text(), 'Неверно введена дата')]").should(appear);
    }

}
