import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;

import java.time.Duration;

public class AppCardDeliveryTest {

    LocalDate currentDate = LocalDate.now().plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String formattedCurrentDate = currentDate.format(formatter);


    @Test
    void shouldPositiveTest() {
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formattedCurrentDate);
        $("[data-test-id='name'] input").setValue("Римский-Корсаков Николай");
        $("[data-test-id='phone'] input").setValue("+78008008080");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + formattedCurrentDate), Duration.ofSeconds(15));

    }


}


