package crm.com.CRM.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import crm.com.CRM.model.*;

public class PaymentWrapper {
    @JsonProperty("payment")
    private Payment payment;
    
    @JsonProperty("invoice")
    private Invoice invoice;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
        if (payment != null) {
            payment.setInvoice(invoice);
        }
    }
}