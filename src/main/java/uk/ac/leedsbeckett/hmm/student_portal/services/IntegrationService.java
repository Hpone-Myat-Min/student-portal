package uk.ac.leedsbeckett.hmm.student_portal.services;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.ac.leedsbeckett.hmm.student_portal.entities.FinanceAccount;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Invoice;

@Component
public class IntegrationService {

    private final RestTemplate restTemplate;

    public IntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FinanceAccount getFinanceAccount( String studentId ) {
        return restTemplate.getForObject("http://localhost:8081/accounts/student/" + studentId, FinanceAccount.class);
    }

    public FinanceAccount createFinanceAccount( FinanceAccount financeAccount ) {
        return restTemplate.postForObject("http://localhost:8081/accounts", financeAccount, FinanceAccount.class);
    }

    public Invoice createCourseFeeInvoice( Invoice invoice ) {
        return restTemplate.postForObject("http://localhost:8081/invoices/", invoice, Invoice.class);
    }

}
