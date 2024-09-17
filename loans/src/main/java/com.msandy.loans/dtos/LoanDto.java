package com.msandy.loans.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Schema(name = "Loan Account Record", description = "Schema to hold Customer's Loan Account info")
public class LoanDto {
    @Schema(name="mobileNumber",description = "Mobile Number, Field to store the contact of customer")
    @NotEmpty(message="Mobile Number Can't Be Empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits)")
    private String mobileNumber;

    @Schema(name="loanNumber",description = "Loan Number, Field to store the banking account of customer")
    @NotEmpty(message="Loan Number Can't Be Empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "Loan Number must be 10 digits)")
    private String loanNumber;

    @Schema(name="loanType",description = "Loan Type, Field to store loan a/c Type e.g. Car/Home/Business ")
    @NotEmpty(message="Loan Type Can't Be Null/Empty")
    private String loanType;

    @Schema(name="totalLoan",description = "Total loan amount", example = "100000")
    @NotEmpty(message="Total Loan Can't Be Null/Empty")
    @Positive(message="Total loan amount should be greater than zero")
    @Column(name="total_loan")
    private int totalLoan;

    @Schema(description = "Total loan amount paid", example = "1000")
    @PositiveOrZero(message="Total loan amount paid should be equal or greater than zero")
    @Column(name="amount_paid")
    private int amountPaid;

    @Schema(description = "Total outstanding amount against a loan", example = "99000")
    @PositiveOrZero(message="Total outstanding amount should be equal or greater than zero")
    @Column(name="outstanding_amount")
    private int outstandingAmount;
}