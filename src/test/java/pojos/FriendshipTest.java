package pojos;

import org.junit.Test;
import pojos.Friendship;
import static org.junit.Assert.*;
public class FriendshipTest {
    @Test
    public void TestConstructerNoUsers() {
        Friendship relation1 = new Friendship();
        assertTrue(relation1.getReceiver() == null);
        assertTrue(relation1.getSender() == null);
        assertTrue(relation1.getStatus() == false);
    }
    @Test
    public void TestConstructerWithUsers() {
        String user1 = "Meepo";
        String user2 = "Rylai";
        Friendship relation1 = new Friendship(user1, user2);
        assertTrue(relation1.getSender() == user1);
        assertTrue(relation1.getReceiver() == user2);
        assertTrue(relation1.getStatus() == false);
    }
    @Test
    public void TestAcceptFriendship() {
        String user1 = "Meepo";
        String user2 = "Rylai";
        Friendship relation1 = new Friendship(user1, user2);
        relation1.setStatus(true);
        assertTrue(relation1.getStatus() == true);
    }
    @Test
    public void TestSetReceiver() {
        String user1 = "Meepo";
        String user2 = "Rylai";
        Friendship relation1 = new Friendship(user1, user2);
        relation1.setReceiver("Lanaya");
        assertTrue(relation1.getReceiver() == "Lanaya");
    }
    @Test
    public void TestSetSender() {
        String user1 = "Meepo";
        String user2 = "Rylai";
        Friendship relation1 = new Friendship(user1, user2);
        relation1.setSender("Lanaya");
        assertTrue(relation1.getSender() == "Lanaya");
    }
    @Test
    public void TestEqualsTrue() {
        String user1 = "Meepo";
        String user2 = "Rylai";
        Friendship relation1 = new Friendship(user1, user2);
        Friendship relation2 = new Friendship(user1, user2);
        assertTrue(relation1.equals(relation2));
    }

    @Test
    public void TestEqualsFalse() {
        String user1 = "Meepo";
        String user2 = "Rylai";
        Friendship relation1 = new Friendship(user1, user2);
        Friendship relation2 = new Friendship(user2, user1);
        assertFalse(relation1.equals(relation2));
    }
}
