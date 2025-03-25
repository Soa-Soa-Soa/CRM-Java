package crm.com.CRM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import crm.com.CRM.model.*;
import crm.com.CRM.service.DashboardService;
import crm.com.CRM.service.TokenStorage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
    
    private final DashboardService dashboardService;
    private final TokenStorage tokenStorage;

    @Autowired
    public DashboardController(DashboardService dashboardService, TokenStorage tokenStorage) {
        this.dashboardService = dashboardService;
        this.tokenStorage = tokenStorage;
    }

    @GetMapping
    public String getDashboard(Model model) {
        if (tokenStorage.getToken() == null) {
            return "redirect:/login";
        }
        
        try {
            DashboardStats stats = dashboardService.getDashboardStats();
            model.addAttribute("stats", stats);
            
            // Récupérer les données pour les graphiques
            List<Map<String, Object>> paymentStats = dashboardService.getPaymentStats();
            List<Map<String, Object>> projectStats = dashboardService.getProjectStats();
            List<Map<String, Object>> invoiceStats = dashboardService.getInvoiceStats();

            // Vérifier que les données ne sont pas nulles
            if (paymentStats == null) paymentStats = Collections.emptyList();
            if (projectStats == null) projectStats = Collections.emptyList();
            if (invoiceStats == null) invoiceStats = Collections.emptyList();

            model.addAttribute("paymentStats", paymentStats);
            model.addAttribute("projectStats", projectStats);
            model.addAttribute("invoiceStats", invoiceStats);

            return "dashboard";
        } catch (Exception e) {
            logger.error("Error loading dashboard: {}"+ e.getMessage());
            model.addAttribute("error", "Erreur lors du chargement du dashboard: " + e.getMessage());
            // En cas d'erreur, initialiser avec des listes vides
            model.addAttribute("paymentStats", Collections.emptyList());
            model.addAttribute("projectStats", Collections.emptyList());
            model.addAttribute("invoiceStats", Collections.emptyList());
            return "dashboard";
        }
    }

    @GetMapping("/clients")
    public String getClients(Model model) {
        if (tokenStorage.getToken() == null) {
            return "redirect:/login";
        }
        
        try {
            List<Client> clients = dashboardService.getClients();
            System.out.print("Nombre de clients récupérés : {}"+ clients.size());
            model.addAttribute("clients", clients);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des clients", e);
            model.addAttribute("error", "Erreur lors de la récupération des clients : " + e.getMessage());
        }
        return "clients";
    }

    @GetMapping("/projects")
    public String getProjects(Model model) {
        if (tokenStorage.getToken() == null) {
            return "redirect:/login";
        }
        
        try {
            List<Project> projects = dashboardService.getProjects();
            System.out.print("Retrieved {} projects"+ projects.size());
            model.addAttribute("projects", projects);
            return "projects";
        } catch (Exception e) {
            logger.error("Error loading projects: {}"+ e.getMessage());
            model.addAttribute("error", "Erreur lors du chargement des projets: " + e.getMessage());
            return "projects";
        }
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        if (tokenStorage.getToken() == null) {
            return "redirect:/login";
        }
        
        try {
            List<Task> tasks = dashboardService.getTasks();
            System.out.print("Retrieved {} tasks"+ tasks.size());
            model.addAttribute("tasks", tasks);
            return "tasks";
        } catch (Exception e) {
            logger.error("Error loading tasks: {}"+ e.getMessage());
            model.addAttribute("error", "Erreur lors du chargement des tâches: " + e.getMessage());
            return "tasks";
        }
    }

    @GetMapping("/offers")
    public String getOffers(Model model) {
        if (tokenStorage.getToken() == null) {
            return "redirect:/login";
        }
        
        try {
            List<Offer> offers = dashboardService.getOffers();
            System.out.print("Retrieved {} offers"+ offers.size());
            model.addAttribute("offers", offers);
            return "offers";
        } catch (Exception e) {
            logger.error("Error loading offers: {}"+ e.getMessage());
            model.addAttribute("error", "Erreur lors du chargement des offres: " + e.getMessage());
            return "offers";
        }
    }

    @GetMapping("/invoices")
    public String getInvoices(Model model) {
        try {
            System.out.print("Fetching invoices from service");
            List<Invoice> invoices = dashboardService.getInvoices();
            System.out.print("Retrieved {} invoices from service"+ invoices.size());
            model.addAttribute("invoices", invoices);
        } catch (Exception e) {
            logger.error("Error fetching invoices: {}"+ e.getMessage(), e);
            model.addAttribute("error", "Une erreur s'est produite lors de la récupération des factures.");
        }
        return "invoices";
    }

    @GetMapping("/payments")
    public String getPayments(Model model) {
        if (tokenStorage.getToken() == null) {
            return "redirect:/login";
        }
        
        try {
            List<Payment> payments = dashboardService.getPayments();
            System.out.print("Retrieved {} payments"+ payments.size());
            model.addAttribute("payments", payments);
            return "payments";
        } catch (Exception e) {
            logger.error("Error loading payments: {}"+ e.getMessage());
            model.addAttribute("error", "Erreur lors du chargement des paiements: " + e.getMessage());
            return "payments";
        }
    }
}
