package uk.ac.leedsbeckett.hmm.student_portal.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceAccount {
    // Finance Data Transfer Object to map response from Finance API

    private Long id;
    private String studentId;
    private boolean hasOutstandingBalance;

}
