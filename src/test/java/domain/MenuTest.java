package domain;

import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.Price;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MenuTest {
    private Menu makeMenu() {
        Menu newMenu = new Menu("americano", "Not good.");
        return newMenu;
    }

    @Test
    public void priceTest() {
        Menu newMenu = makeMenu();
        assertEquals(newMenu.getPricePerSize().size(), 0);

        Price testPrice = new Price("small", 2000);
        newMenu.getPricePerSize().add(testPrice);
        assertEquals(newMenu.getPricePerSize().size(), 1);
        assertEquals(newMenu.getPricePerSize().get(0), testPrice);
    }
}
