package uk.ac.leedsbeckett.hmm.student_portal.entities;
import com.fasterxml.jackson.annotation.JsonFormat;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Invoice {

    private Double amount;
    private String reference;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private Type type;
    private Status status;
    private String studentId;
    private FinanceAccount account;

    public enum Type{
        LIBRARY_FINE,
        TUITION_FEES
    }

    public enum Status{
        OUTSTANDING,
        PAID,
        CANCELLED
    }

}
