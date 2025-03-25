package crm.com.CRM.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import crm.com.CRM.model.Client;
import crm.com.CRM.model.ContactInfo;
import crm.com.CRM.model.User;

public class ClientWrapper {
    @JsonProperty("client")
    private Client client;
    
    @JsonProperty("contactInfo")
    private ContactInfo[] contactInfo;
    
    @JsonProperty("user")
    private User user;

    // Getters and Setters
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ContactInfo[] getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo[] contactInfo) {
        this.contactInfo = contactInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
