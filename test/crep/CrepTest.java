package crep;

import org.junit.Test;
import java.util.function.Function;
import static org.junit.Assert.*;

public class CrepTest {

    private final Function<String, Boolean> fun1 = (String s) -> s.equals("-r");
    private final Function<String, Boolean> fun2 = (String s) -> s.equals("-v");
    private final Function<String, Boolean> fun3 = (String s) -> s.equals("-i");

    private final Crep crep1 = new Crep("(регулярные выражения это круто)", "files/RegEx");
    private final Crep crep2 = new Crep("[0-9]", "files/AomameNumbers");
    private final Crep crep3 = new Crep("Аомамэ", "files/Aomame");
    private final Crep crep4 = new Crep("АоМаМэ", "files/Aomame");
    private final Crep crep5 = new Crep(":", "files/Aomame");
    private final Crep crep6 = new Crep("хозяйк", "files/Aomame");
    private final Crep crep7 = new Crep("[А-Я]", "files/RegEx");

    @Test
    public void creper() throws Exception {
        assertEquals("регулярные выражения это круто\n" +
                "регулярные выражения это круто\n" +
                "регулярные выражения это круто\n" +
                "регулярные выражения это круто", crep1.creper(fun1));
        assertEquals("1 Сидя у себя на балконе, Аомамэ видит на небе две луны: одна обычная жёлтая, вторая поменьше, зеленоватая.\n" +
                "3 Дочь хозяйки тоже совершила суицид, не выдержав домашнего насилия.\n" +
                "5 Хозяйка попросила у Аомамэ помощи: только она, обладая мануальными способностями, могла убить садиста, не оставляя следов.", crep2.creper(fun1));
        assertEquals("Я СЕГОДНЯ ЕЛА ИЗЮМ", crep7.creper(fun1));
        assertEquals("3 Дочь хозяйки тоже совершила суицид, не выдержав домашнего насилия.\n" +
                "4 Мать лишила мужа дочери всего, что он имел в жизни, а затем учредила приют.", crep3.creper(fun2));
        assertEquals("2 С хозяйкой они сблизились, когда Аомамэ рассказала той об убийстве мужа Тамаки.\n" +
                "3 Дочь хозяйки тоже совершила суицид, не выдержав домашнего насилия.\n" +
                "4 Мать лишила мужа дочери всего, что он имел в жизни, а затем учредила приют.\n" +
                "6 Аомамэ согласилась.", crep5.creper(fun2));
        assertEquals("1 Сидя у себя на балконе, Аомамэ видит на небе две луны: одна обычная жёлтая, вторая поменьше, зеленоватая.\n" +
                "2 С хозяйкой они сблизились, когда Аомамэ рассказала той об убийстве мужа Тамаки.\n" +
                "5 Хозяйка попросила у Аомамэ помощи: только она, обладая мануальными способностями, могла убить садиста, не оставляя следов.\n" +
                "6 Аомамэ согласилась.", crep4.creper(fun3));
        assertEquals("2 С хозяйкой они сблизились, когда Аомамэ рассказала той об убийстве мужа Тамаки.\n" +
                "3 Дочь хозяйки тоже совершила суицид, не выдержав домашнего насилия.\n" +
                "5 Хозяйка попросила у Аомамэ помощи: только она, обладая мануальными способностями, могла убить садиста, не оставляя следов.", crep6.creper(fun3));
    }
}