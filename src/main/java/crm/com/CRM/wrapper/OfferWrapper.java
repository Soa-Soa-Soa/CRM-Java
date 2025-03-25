package crm.com.CRM.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import crm.com.CRM.model.*;

public class OfferWrapper {
    @JsonProperty("offer")
    private Offer offer;
    
    @JsonProperty("amount")
    private String amount;

    // Getters and Setters
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
