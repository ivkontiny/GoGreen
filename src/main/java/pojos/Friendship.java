package pojos;

public class Friendship {


    private String sender;
    private String receiver;
    private Boolean accepted;

    public Friendship()
    {
        this.accepted=false;
    }

    public Friendship(String requester, String receiver)
    {
        this.sender = requester;
        this.receiver = receiver;
        this.accepted = false;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String requester) {
        this.sender = requester;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Boolean getStatus() {
        return accepted;
    }

    public void setStatus(Boolean status) {
        this.accepted = status;
    }
}
