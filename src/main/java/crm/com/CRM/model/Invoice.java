package crm.com.CRM.model;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Invoice {
    private Long id;
    
    @JsonProperty("external_id")
    private String externalId;
    
    private String status;

    private Object amount;
    
    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    @JsonProperty("invoice_number")
    private String invoiceNumber;
    
    @JsonProperty("sent_at")
    private LocalDate sentAt;
    
    @JsonProperty("due_at")
    private LocalDate dueAt;
    
    @JsonProperty("integration_invoice_id")
    private String integrationInvoiceId;
    
    @JsonProperty("integration_type")
    private String integrationType;
    
    @JsonProperty("source_type")
    private String sourceType;
    
    @JsonProperty("source_id")
    private Long sourceId;
    
    @JsonProperty("client_id")
    private Long clientId;
    
    @JsonProperty("offer_id")
    private Long offerId;
    
    @JsonProperty("deleted_at")
    private LocalDate deletedAt;
    
    @JsonProperty("created_at")
    private LocalDate createdAt;
    
    @JsonProperty("updated_at")
    private LocalDate updatedAt;

    // Relations from wrapper
    private Client client;
    private List<InvoiceLine> lines;
    private Integer lineNumber;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDate sentAt) {
        this.sentAt = sentAt;
    }

    public LocalDate getDueAt() {
        return dueAt;
    }

    public void setDueAt(LocalDate dueAt) {
        this.dueAt = dueAt;
    }

    public String getIntegrationInvoiceId() {
        return integrationInvoiceId;
    }

    public void setIntegrationInvoiceId(String integrationInvoiceId) {
        this.integrationInvoiceId = integrationInvoiceId;
    }

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<InvoiceLine> getLines() {
        return lines;
    }

    public void setLines(List<InvoiceLine> lines) {
        this.lines = lines;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    // Utility method to calculate total amount
    public Double getTotalAmount() {
        if (lines == null) return 0.0;
        return lines.stream()
            .mapToDouble(line -> line.getPrice() * line.getQuantity())
            .sum();
    }
}
