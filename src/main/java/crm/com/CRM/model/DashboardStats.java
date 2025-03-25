package crm.com.CRM.model;


import crm.com.CRM.model.*;

public class DashboardStats {
    private Long totalClients;
    private Long totalProjects;
    private Long totalTasks;
    private Long totalOffers;
    private Long totalInvoices;
    private Long totalPayments;

    // Constructors
    public DashboardStats() {}

    // Getters and Setters
    public Long getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(Long totalClients) {
        this.totalClients = totalClients;
    }

    public Long getTotalProjects() {
        return totalProjects;
    }

    public void setTotalProjects(Long totalProjects) {
        this.totalProjects = totalProjects;
    }

    public Long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(Long totalTasks) {
        this.totalTasks = totalTasks;
    }

    public Long getTotalOffers() {
        return totalOffers;
    }

    public void setTotalOffers(Long totalOffers) {
        this.totalOffers = totalOffers;
    }

    public Long getTotalInvoices() {
        return totalInvoices;
    }

    public void setTotalInvoices(Long totalInvoices) {
        this.totalInvoices = totalInvoices;
    }

    public Long getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Long totalPayments) {
        this.totalPayments = totalPayments;
    }
}
