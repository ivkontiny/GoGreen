package services;

import database.DefaultValueDao;
import org.junit.Test;
import pojos.Category;
import pojos.DefaultValue;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DefaultValueServiceTest {

    DefaultValueService dvs = new DefaultValueService();
    DefaultValueDao db = new DefaultValueDao();

    @Test
    public void testGetValuesFromCategory() {
        ArrayList<String> exp = new ArrayList<>();
        DefaultValue dv = new DefaultValue("tomato", Category.food, "kg", 25, 0.5);
        assertEquals(exp, dvs.getValuesFromCategory(Category.energy));
        assertTrue(dvs.createDefaultValue(dv));
        exp.add("tomato");
        assertEquals(exp, dvs.getValuesFromCategory(Category.food));
        dvs.deleteDefaultValue(dv.getDescription());
    }
}