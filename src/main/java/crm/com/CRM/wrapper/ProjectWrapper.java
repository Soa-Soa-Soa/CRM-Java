package crm.com.CRM.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import crm.com.CRM.model.*;

public class ProjectWrapper {
    @JsonProperty("project")
    private Project project;
    
    @JsonProperty("status")
    private Status status;
    
    @JsonProperty("user")
    private User user;
    
    @JsonProperty("client")
    private Client client;

    // Getters and Setters
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
