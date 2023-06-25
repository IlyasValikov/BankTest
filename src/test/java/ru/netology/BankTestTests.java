package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



class BankTestTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void test() {
        SelenideElement name= $("[data-test-id=name] input").setValue("Иванов Иван");
        SelenideElement phone= $("[data-test-id=phone] input").setValue("+12345678987");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));


    }

    @Test
    void invalidNameTest1() {
        SelenideElement name= $("[data-test-id=name] input").setValue("IvanovIvan");
        SelenideElement phone= $("[data-test-id=phone] input").setValue("+12345678987");
        $("[data-test-id=agreement]").click();
        $("button").click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = $(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void invalidPhoneTest1() {
        SelenideElement name= $("[data-test-id=name] input").setValue("Иванов Иван");
        SelenideElement phone= $("[data-test-id=phone] input").setValue("+1234567898788");
        $("[data-test-id=agreement]").click();
        $("button").click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = $(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void invalidNameTest2() {
        SelenideElement name= $("[data-test-id=name] input").setValue("");
        SelenideElement phone= $("[data-test-id=phone] input").setValue("+12345678987");
        $("[data-test-id=agreement]").click();
        $("button").click();
        String expected = "Поле обязательно для заполнения";
        String actual = $(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void invalidPhoneTest2() {
        SelenideElement name= $("[data-test-id=name] input").setValue("Иванов Иван");
        SelenideElement phone= $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        String expected = "Поле обязательно для заполнения";
        String actual = $(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void invalidCheckboxTest() {
        SelenideElement name= $("[data-test-id=name] input").setValue("Иванов Иван");
        SelenideElement phone= $("[data-test-id=phone] input").setValue("");
        WebElement checkbox = $("[data-test-id=agreement]");
        boolean click = checkbox.isSelected();
        Assertions.assertFalse(click);
        $("button").click();

    }

}
