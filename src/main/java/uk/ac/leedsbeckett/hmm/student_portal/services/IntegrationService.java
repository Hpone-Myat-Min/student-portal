package uk.ac.leedsbeckett.hmm.student_portal.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.ac.leedsbeckett.hmm.student_portal.entities.FinanceAccount;
import uk.ac.leedsbeckett.hmm.student_portal.entities.Invoice;
import uk.ac.leedsbeckett.hmm.student_portal.entities.LibraryAccount;

import java.util.Arrays;
import java.util.List;

@Component
public class IntegrationService {

    private final RestTemplate restTemplate;

    public IntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    public FinanceAccount getFinanceAccount( String studentId ) {
//        return restTemplate.getForObject("http://localhost:8081/accounts/student/" + studentId, FinanceAccount.class);
//    }
//
//    public FinanceAccount createFinanceAccount( FinanceAccount financeAccount ) {
//        return restTemplate.postForObject("http://localhost:8081/accounts", financeAccount, FinanceAccount.class);
//    }
//
//    public Invoice createCourseFeeInvoice( Invoice invoice ) {
//        return restTemplate.postForObject("http://localhost:8081/invoices/", invoice, Invoice.class);
//    }
//
//    public Invoice getInvoice(String reference){
//        return restTemplate.getForObject("http://localhost:8081/invoices/reference/" + reference, Invoice.class);
//    }
//
//    public Invoice payInvoice(String reference) {
//        ResponseEntity<Invoice> response = restTemplate.exchange(
//                "http://localhost:8081/invoices/{reference}/pay",
//                HttpMethod.PUT,
//                HttpEntity.EMPTY,
//                Invoice.class,
//                reference
//        );
//        return response.getBody();
//    }

    public FinanceAccount getFinanceAccount( String studentId ) {
        return restTemplate.getForObject("http://financeapp:8081/accounts/student/" + studentId, FinanceAccount.class);
    }

    public FinanceAccount createFinanceAccount( FinanceAccount financeAccount ) {
        return restTemplate.postForObject("http://financeapp:8081/accounts", financeAccount, FinanceAccount.class);
    }

    public Invoice createCourseFeeInvoice( Invoice invoice ) {
        return restTemplate.postForObject("http://financeapp:8081/invoices/", invoice, Invoice.class);
    }

    public Invoice getInvoice(String reference){
        return restTemplate.getForObject("http://financeapp:8081/invoices/reference/" + reference, Invoice.class);
    }

    public List<Invoice> getAllInvoices(){
        Invoice[] allInvoices = restTemplate.getForObject("http://financeapp:8081/invoices/", Invoice[].class);
        return Arrays.asList(allInvoices);
    }

//    public Invoice payInvoice(String reference) {
//        ResponseEntity<Invoice> response = restTemplate.exchange(
//                "http://financeapp:8081/invoices/{reference}/pay",
//                HttpMethod.PUT,
//                HttpEntity.EMPTY,
//                Invoice.class,
//                reference
//        );
//        return response.getBody();
//    }


//    public LibraryAccount createLibraryAccount(String studentId ) {
//        LibraryAccount newLibAcc = new LibraryAccount();
//        newLibAcc.setStudentId(studentId);
//
//        return restTemplate.postForObject("http://libraryapp:80/api/register", newLibAcc, LibraryAccount.class);
//
//    }

    public LibraryAccount createLibraryAccount(String studentId ) {
        LibraryAccount newLibAcc = new LibraryAccount();
        newLibAcc.setStudentId(studentId);

        return restTemplate.postForObject("http://libraryportal:8082/libraryAccount/create", newLibAcc, LibraryAccount.class);

    }

}
