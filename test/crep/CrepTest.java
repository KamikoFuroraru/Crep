package crep;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CrepTest {

    private final Crep crep1 = new Crep("(регулярные выражения это круто)", "files/RegEx");
    private final Crep crep2 = new Crep("[0-9]", "files/AomameNumbers");
    private final Crep crep3 = new Crep("Аомамэ", "files/Aomame");
    private final Crep crep4 = new Crep("АоМаМэ", "files/Aomame");
    private final Crep crep5 = new Crep(":", "files/Aomame");
    private final Crep crep6 = new Crep("(рЕгУлЯрНыЕ вЫрАжЕнИя ЭтО кРуТо)", "files/RegEx");
    private final Crep crep7 = new Crep("(якороль)", "files/RegEx");
    private final Crep crep8 = new Crep("ИЗЮМ", "files/RegEx");

    @Test
    public void creper() throws Exception {

        assertEquals("регулярные выражения это круто\n" +
                "регулярные выражения это круто\n" +
                "регулярные выражения это круто\n" +
                "регулярные выражения это круто", crep1.creper(true, false, false));
        assertEquals("1 Сидя у себя на балконе, Аомамэ видит на небе две луны: одна обычная жёлтая, вторая поменьше, зеленоватая.\n" +
                "2 С хозяйкой они сблизились, когда Аомамэ рассказала той об убийстве мужа Тамаки.\n" +
                "5 Хозяйка попросила у Аомамэ помощи: только она, обладая мануальными способностями, могла убить садиста, не оставляя следов.\n" +
                "6 Аомамэ согласилась.", crep4.creper(false, false, true));
        assertEquals("3 Дочь хозяйки тоже совершила суицид, не выдержав домашнего насилия.\n" +
                "4 Мать лишила мужа дочери всего, что он имел в жизни, а затем учредила приют.", crep3.creper(false, true, false));
        assertEquals("якороль\n" +
                "Я СЕГОДНЯ ЕЛА ИЗЮМ\n" +
                "якороль", crep6.creper(true, true, true));
        assertEquals("2 С хозяйкой они сблизились, когда Аомамэ рассказала той об убийстве мужа Тамаки.\n" +
                "3 Дочь хозяйки тоже совершила суицид, не выдержав домашнего насилия.\n" +
                "4 Мать лишила мужа дочери всего, что он имел в жизни, а затем учредила приют.\n" +
                "6 Аомамэ согласилась.", crep5.creper(false, true, true));
        assertEquals(" С хозяйкой они сблизились, когда Аомамэ рассказала той об убийстве мужа Тамаки.\n" +
                " Мать лишила мужа дочери всего, что он имел в жизни, а затем учредила приют.\n" +
                " Аомамэ согласилась.", crep2.creper(true, true, false));
        assertEquals("якороль\n" +
                "якороль", crep7.creper(true, false, true));
        assertEquals("Я СЕГОДНЯ ЕЛА ИЗЮМ", crep8.creper(false, false, false));
    }
}