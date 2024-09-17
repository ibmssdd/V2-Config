package com.msandy.loans.services.intf;

import com.msandy.loans.dtos.LoanDto;

public interface IntfSvcLoan
{
    void    createNewLoan(String mobileNumber);
    LoanDto inqLoanByMobile(String mobileNumber);
    boolean updLoanDetails(LoanDto loanDto);
    boolean delLoanDetails(String mobileNumber);
}
