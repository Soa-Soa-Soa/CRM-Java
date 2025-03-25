package crm.com.CRM.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import crm.com.CRM.model.*;

public class Payment {
    private Long id;
    
    @JsonProperty("external_id")
    private String externalId;
    
    private Integer amount;
    private String description;
    
    @JsonProperty("payment_source")
    private String paymentSource;
    
    @JsonProperty("payment_date")
    private LocalDate paymentDate;
    
    @JsonProperty("integration_payment_id")
    private String integrationPaymentId;
    
    @JsonProperty("integration_type")
    private String integrationType;
    
    @JsonProperty("invoice_id")
    private Integer invoiceId;

    private Invoice invoice;

    @JsonProperty("deleted_at")
    private LocalDate deletedAt;
    
    @JsonProperty("created_at")
    private LocalDate createdAt;
    
    @JsonProperty("updated_at")
    private LocalDate updatedAt;

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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

    // Getters
    public Long getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getPaymentSource() {
        return paymentSource;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getIntegrationPaymentId() {
        return integrationPaymentId;
    }

    public String getIntegrationType() {
        return integrationType;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPaymentSource(String paymentSource) {
        this.paymentSource = paymentSource;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setIntegrationPaymentId(String integrationPaymentId) {
        this.integrationPaymentId = integrationPaymentId;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
}
