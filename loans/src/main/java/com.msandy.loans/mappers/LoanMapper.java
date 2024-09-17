package com.msandy.loans.mappers;

import com.msandy.loans.dtos.LoanDto;
import com.msandy.loans.entities.LoanEntity;
import lombok.*;

import java.util.Optional;
@Getter @Setter @ToString
public class LoanMapper
{
        public static LoanDto mapLoanToDto(LoanDto loanDto, LoanEntity loanEntity)
    {   loanDto.setLoanNumber(loanEntity.getLoanNumber());
        loanDto.setMobileNumber(loanEntity.getMobileNumber());
        loanDto.setLoanType(loanEntity.getLoanType());
        loanDto.setTotalLoan(loanEntity.getTotalLoan());
        loanDto.setAmountPaid(loanEntity.getAmountPaid());
        loanDto.setOutstandingAmount(loanEntity.getOutstandingAmount());
        return loanDto;
    }
    public static LoanEntity mapDtoToLoan(LoanDto loanDto,LoanEntity loanEntity)
    {   loanEntity.setLoanNumber(loanDto.getLoanNumber());
        loanEntity.setMobileNumber(loanDto.getMobileNumber());
        loanEntity.setLoanType(loanDto.getLoanType());
        loanEntity.setTotalLoan(loanDto.getTotalLoan());
        loanEntity.setAmountPaid(loanDto.getAmountPaid());
        loanEntity.setOutstandingAmount(loanDto.getOutstandingAmount());
        return loanEntity;
    }
}
