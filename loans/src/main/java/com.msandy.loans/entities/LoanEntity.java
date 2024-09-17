package com.msandy.loans.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class LoanEntity extends BaseEntity
{
    @Column(name = "loan_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanID;

    @Column(name = "mobile_number")
    @NotNull
    private String mobileNumber;

    @Column(name = "loan_number")
    @NotNull
    private String loanNumber;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;
}