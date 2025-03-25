package crm.com.CRM.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import crm.com.CRM.model.*;

import java.util.List;

public class InvoiceWrapper {
    @JsonProperty("invoice")
    private Invoice invoice;
    
    @JsonProperty("client")
    private Client client;
    
    @JsonProperty("lines")
    private List<InvoiceLine> lines;
    
    @JsonProperty("lineNumber")
    private Integer lineNumber;

    @JsonProperty("amount")
    private Object amount;

    @JsonProperty("amount")
    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public Object getAmount() {
        return amount;
    }

    // Getters and Setters
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
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
}
