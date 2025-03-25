package crm.com.CRM.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.annotation.PostConstruct;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import crm.com.CRM.model.*;
import crm.com.CRM.wrapper.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DashboardService {
    
    private static final Logger logger = LoggerFactory.getLogger(DashboardService.class);
    
    @Value("${php.api.base-url}")
    private String phpApiBaseUrl;
    
    private final RestTemplate restTemplate;
    private final TokenStorage tokenStorage;
    @Autowired
    private ObjectMapper objectMapper;

    public DashboardService(RestTemplate restTemplate, TokenStorage tokenStorage) {
        this.restTemplate = restTemplate;
        this.tokenStorage = tokenStorage;
    }

    @PostConstruct
    public void init() {
        restTemplate.getMessageConverters().add(0, 
            new MappingJackson2HttpMessageConverter(objectMapper));
    }

    public DashboardStats getDashboardStats() {
        DashboardStats stats = new DashboardStats();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tokenStorage.getToken());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        stats.setTotalClients(fetchCount("/api/dashboard/clients/count", entity));
        stats.setTotalProjects(fetchCount("/api/dashboard/projects/count", entity));
        stats.setTotalTasks(fetchCount("/api/dashboard/tasks/count", entity));
        stats.setTotalOffers(fetchCount("/api/dashboard/offers/count", entity));
        stats.setTotalInvoices(fetchCount("/api/dashboard/invoices/count", entity));
        stats.setTotalPayments(fetchCount("/api/dashboard/payments/count", entity));
        
        return stats;
    }

    public List<Client> getClients() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/clients";
            System.out.print("Fetching clients from URL: {}"+ url);
            
            ResponseEntity<ClientWrapper[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ClientWrapper[].class
            );
            
            ClientWrapper[] wrappers = response.getBody();
            List<Client> clients = new ArrayList<>();
            
            if (wrappers != null) {
                for (ClientWrapper wrapper : wrappers) {
                    Client client = wrapper.getClient();
                    if (client != null) {
                        client.setContactInfo(Arrays.asList(wrapper.getContactInfo()));
                        client.setUser(wrapper.getUser());
                        clients.add(client);
                    }
                }
            }
            
            System.out.print("Retrieved {} clients"+ clients.size());
            return clients;
            
        } catch (Exception e) {
            logger.error("Error fetching clients: {}"+ e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Project> getProjects() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/projects";
            System.out.print("Fetching projects from URL: {}"+ url);
            
            ResponseEntity<ProjectWrapper[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                ProjectWrapper[].class
            );
            
            ProjectWrapper[] wrappers = response.getBody();
            List<Project> projects = new ArrayList<>();
            
            if (wrappers != null) {
                for (ProjectWrapper wrapper : wrappers) {
                    Project project = wrapper.getProject();
                    if (project != null) {
                        project.setStatus(wrapper.getStatus());
                        project.setUser(wrapper.getUser());
                        project.setClient(wrapper.getClient());
                        projects.add(project);
                    }
                }
            }
            
            System.out.print("Retrieved {} projects"+ projects.size());
            return projects;
            
        } catch (Exception e) {
            logger.error("Error fetching projects: {}"+ e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Task> getTasks() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/tasks";
            System.out.print("Fetching tasks from URL: {}"+ url);
            
            ResponseEntity<TaskWrapper[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                TaskWrapper[].class
            );
            
            TaskWrapper[] wrappers = response.getBody();
            List<Task> tasks = new ArrayList<>();
            
            if (wrappers != null) {
                for (TaskWrapper wrapper : wrappers) {
                    Task task = wrapper.getTask();
                    if (task != null) {
                        task.setStatus(wrapper.getStatus());
                        task.setUser(wrapper.getUser());
                        task.setClient(wrapper.getClient());
                        task.setProject(wrapper.getProject());
                        tasks.add(task);
                    }
                }
            }
            
            System.out.print("Retrieved {} tasks"+ tasks.size());
            return tasks;
            
        } catch (Exception e) {
            logger.error("Error fetching tasks: {}"+ e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Offer> getOffers() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/offers";
            System.out.print("Fetching offers from URL: {}"+ url);
            
            ResponseEntity<OfferWrapper[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                OfferWrapper[].class
            );
            
            OfferWrapper[] wrappers = response.getBody();
            List<Offer> offers = new ArrayList<>();
            
            if (wrappers != null) {
                for (OfferWrapper wrapper : wrappers) {
                    Offer offer = wrapper.getOffer();
                    if (offer != null) {
                        offer.setAmount(wrapper.getAmount());
                        offers.add(offer);
                    }
                }
            }
            
            System.out.print("Retrieved {} offers"+ offers.size());
            return offers;
            
        } catch (Exception e) {
            logger.error("Error fetching offers: {}"+ e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Invoice> getInvoices() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/invoices";
            System.out.print("Fetching invoices from URL: {}"+ url);
            
            ResponseEntity<InvoiceWrapper[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                InvoiceWrapper[].class
            );
            
            InvoiceWrapper[] wrappers = response.getBody();
            System.out.print("Received response body: {}"+ wrappers);
            
            List<Invoice> invoices = new ArrayList<>();
            
            if (wrappers != null) {
                System.out.print("Number of wrappers received: {}" + wrappers.length);
                for (InvoiceWrapper wrapper : wrappers) {
                    Invoice invoice = wrapper.getInvoice();
                    if (invoice != null) {
                        System.out.print("Processing invoice with ID: {}"+ invoice.getId());
                        invoice.setAmount(wrapper.getAmount());
                        invoice.setClient(wrapper.getClient());
                        invoice.setLines(wrapper.getLines());
                        invoice.setLineNumber(wrapper.getLineNumber());
                        invoices.add(invoice);
                    } else {
                        System.out.print("Received null invoice in wrapper");
                    }
                }
            } else {
                logger.warn("Received null response body");
            }
            
            System.out.print("Retrieved {} invoices" + invoices.size());
            return invoices;
            
        } catch (Exception e) {
            logger.error("Error fetching invoices: {}"+ e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Payment> getPayments() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/payments";
            logger.info("Fetching payments from URL: {}", url);
            
            ResponseEntity<PaymentWrapper[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                PaymentWrapper[].class
            );
            
            PaymentWrapper[] wrappers = response.getBody();
            List<Payment> payments = new ArrayList<>();
            
            if (wrappers != null) {
                for (PaymentWrapper wrapper : wrappers) {
                    Payment payment = wrapper.getPayment();
                    if (payment != null) {
                        payment.setInvoice(wrapper.getInvoice());
                        payments.add(payment);
                    }
                }
            }
            
            logger.info("Retrieved {} payments", payments.size());
            return payments;
            
        } catch (Exception e) {
            logger.error("Error fetching payments: {}", e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private Long fetchCount(String endpoint, HttpEntity<?> entity) {
        String url = phpApiBaseUrl + endpoint;
        System.out.print("Fetching count from URL: {}"+ url);
        ResponseEntity<Long> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            Long.class
        );
        Long result = response.getBody();
        System.out.print("Received count: {}"+ result);
        return result;
    }
    
    public void updatePayment(Payment payment) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<Payment> entity = new HttpEntity<>(payment, headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/payments/" + payment.getExternalId();
            restTemplate.exchange(
                url,
                HttpMethod.PUT,
                entity,
                Void.class
            );
            
            logger.info("Payment {} updated successfully", payment.getId());
        } catch (Exception e) {
            logger.error("Error updating payment: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to update payment", e);
        }
    }

    public Payment getPaymentByExternalId(String externalId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/payments/" + externalId;
            ResponseEntity<PaymentWrapper> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                PaymentWrapper.class
            );
            
            PaymentWrapper wrapper = response.getBody();
            if (wrapper != null && wrapper.getPayment() != null) {
                return wrapper.getPayment();
            }
            return null;
        } catch (Exception e) {
            logger.error("Error fetching payment: {}", e.getMessage(), e);
            return null;
        }
    }

    public void deletePayment(String externalId) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/payments/" + externalId;
            restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                Void.class
            );
            
            logger.info("Payment {} deleted successfully", externalId);
        } catch (Exception e) {
            logger.error("Error deleting payment: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to delete payment", e);
        }
    }

    public List<Map<String, Object>> getPaymentStats() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/stats/payments";
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            logger.error("Error fetching payment stats: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<Map<String, Object>> getProjectStats() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/stats/projects";
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );
            
            List<Map<String, Object>> stats = response.getBody();
            if (stats == null) {
                return Collections.emptyList();
            }
            
            logger.info("Project stats retrieved successfully: {}", stats);
            return stats;
        } catch (Exception e) {
            logger.error("Error fetching project stats: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }
    
    public List<Map<String, Object>> getInvoiceStats() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(tokenStorage.getToken());
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            String url = phpApiBaseUrl + "/api/dashboard/stats/invoices";
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );
            
            List<Map<String, Object>> stats = response.getBody();
            if (stats == null) {
                return Collections.emptyList();
            }
            
            logger.info("Invoice stats retrieved successfully: {}", stats);
            return stats;
        } catch (Exception e) {
            logger.error("Error fetching invoice stats: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
