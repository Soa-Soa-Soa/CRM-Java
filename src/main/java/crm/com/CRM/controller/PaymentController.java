package crm.com.CRM.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import crm.com.CRM.service.DashboardService;
import crm.com.CRM.model.Payment;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final DashboardService dashboardService;

    public PaymentController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/edit/{externalId}")
    public String editPayment(@PathVariable String externalId, Model model) {
        try {
            Payment payment = dashboardService.getPaymentByExternalId(externalId);
            if (payment == null) {
                return "redirect:/dashboard/payments";
            }
            model.addAttribute("payment", payment);
            return "edit-payment";
        } catch (Exception e) {
            return "redirect:/dashboard/payments";
        }
    }

    @PostMapping("/edit")
    public String updatePayment(@ModelAttribute Payment payment) {
        dashboardService.updatePayment(payment);
        return "redirect:/dashboard/payments";
    }

    @PostMapping("/delete/{externalId}")  // Changé de DeleteMapping à PostMapping
    public String deletePayment(@PathVariable String externalId) {
        dashboardService.deletePayment(externalId);
        return "redirect:/dashboard/payments";
    }
}