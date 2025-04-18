package uk.ac.leedsbeckett.hmm.student_portal.entities;
import com.fasterxml.jackson.annotation.JsonFormat;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Invoice {
//    private Long id;
//    private String studentId;
    private Double amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private Type type;
//    private Status status;
    private FinanceAccount account;

    public enum Type{
        LIBRARY_FINE,
        TUITION_FEES
    }

    enum Status{
        OUTSTANDING,
        PAID,
        CANCELLED
    }

//    public static Invoice createInvoice(Student student, Course course, FinanceAccount financeAccount) {
//        Invoice invoice = new Invoice();
//        invoice.setStudentId(student.getStudentId());
//        invoice.setAmount(course.getFee());
//        invoice.setType(Type.TUITION_FEES);
//        invoice.setDate(LocalDate.now());
//        invoice.setStatus(Status.OUTSTANDING);
//        invoice.setFinanceAccount(financeAccount);
//        return invoice;
//    }

}
