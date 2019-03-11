package pojos;

import org.junit.Test;
import pojos.Activity;
import static org.junit.Assert.*;
import pojos.Category;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
public class Activity_test {
	Activity activity;
	@Before
	public void initialize() {
		activity = new Activity();
	}
	@Test
	public void TestDescription() {
		assertEquals(activity.getDescription() , "meal");
	}
	@Test
	public void TestCatagory() {
		assertEquals(activity.getCategory() , null);
	}
	@Test
	public void TestPoints() {
		assertEquals(activity.getPoints() , 10);
	}
	@Test
	public void TestDate() {
		activity.getDate().equals(Date.valueOf(LocalDate.now()));
	}
	@Test
	public void TestUsername() {
		assertEquals(activity.getUsername(), "test");
	}
	@Test
	public void TestSetDescription() {
		activity.setDescription("Cycling");
		assertEquals(activity.getDescription() , "Cycling");
	}
	@Test
	public void TestSetCatagory() {
		activity.setCategory(Category.food);
		assertEquals(activity.getCategory() , Category.food);
	}
	@Test
	public void TestSetPoints() {
		activity.setPoints(30);
		assertEquals(activity.getPoints(), 30);
	}
	@Test
	public void TestSetDate() {
		Date x = new Date(0);
		activity.setDate(x);;
		assertEquals(activity.getDate(), x);
	}
	@Test
	public void TestSetUsername() {
		activity.setUsername("Meepo");
		assertEquals(activity.getUsername(), "Meepo");
	}
	@Test
	public void TestEqualsTrue() {
		Activity activity2 = new Activity();
		assertEquals(activity, activity2);
	}
	@Test
	public void TestEqualsFalse() {
		Date x = new Date(0);
		Activity activity2 = new Activity("Cycling", null, 40, x, "Meepo" );
		assertFalse(activity.equals(activity2));
	}
}
