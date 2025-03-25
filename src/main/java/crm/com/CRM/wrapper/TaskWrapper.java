package crm.com.CRM.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import crm.com.CRM.model.*;

public class TaskWrapper {
    @JsonProperty("task")
    private Task task;
    
    @JsonProperty("status")
    private Status status;
    
    @JsonProperty("user")
    private User user;
    
    @JsonProperty("client")
    private Client client;
    
    @JsonProperty("project")
    private Project project;

    // Getters and Setters
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
