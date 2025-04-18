package uk.ac.leedsbeckett.hmm.student_portal.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceAccount {

    private Long id;
    private String studentId;
    private boolean hasOutstandingBalance;

//    private Set<Invoice> invoices;

}
