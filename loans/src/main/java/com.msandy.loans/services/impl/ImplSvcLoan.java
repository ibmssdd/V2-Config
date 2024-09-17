package com.msandy.loans.services.impl;

import com.msandy.loans.constants.LoanConstants;
import com.msandy.loans.dtos.*;
import com.msandy.loans.entities.*;
import com.msandy.loans.exceptions.LoanAlreadyExistsException;
import com.msandy.loans.exceptions.ResourceNotFoundException;
import com.msandy.loans.mappers.LoanMapper;
import com.msandy.loans.repositories.LoansRepository;
import com.msandy.loans.services.intf.IntfSvcLoan;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service

public class ImplSvcLoan implements IntfSvcLoan
{
    @Autowired
    private LoansRepository loansRepo;

    private static LoanEntity getLoanEntity(Optional<LoanEntity> loanEntityOptional)
        {   LoanEntity loanEntity = new LoanEntity();
            loanEntity.setLoanNumber(loanEntityOptional.get().getLoanNumber());
            loanEntity.setLoanType(loanEntityOptional.get().getLoanType());
            loanEntity.setLoanID(loanEntityOptional.get().getLoanID());
            loanEntity.setTotalLoan(loanEntityOptional.get().getTotalLoan());
            loanEntity.setAmountPaid(loanEntityOptional.get().getAmountPaid());
            loanEntity.setOutstandingAmount(loanEntityOptional.get().getOutstandingAmount());
            loanEntity.setMobileNumber(loanEntityOptional.get().getMobileNumber());
            return loanEntity;
        }

    @Override
    public void createNewLoan(String mobileNumber)
    {
        Optional<LoanEntity> loanEntityOptional = loansRepo.findBymobileNumber(mobileNumber);

        if (loanEntityOptional.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber \"+mobileNumber");}
        else
        {
            LoanEntity tempLoanEntity = new LoanEntity();
            long randomLoanNumber = 200000000000L + new Random().nextInt(900000000);
            tempLoanEntity.setLoanNumber(String.valueOf(randomLoanNumber));
            tempLoanEntity.setMobileNumber(mobileNumber);
            tempLoanEntity.setLoanType(LoanConstants.Loan_Type_Home);
            tempLoanEntity.setTotalLoan(LoanConstants.Loan_Limit_Home);
            tempLoanEntity.setAmountPaid(0);
            tempLoanEntity.setOutstandingAmount(LoanConstants.Loan_Limit_Home);
            loansRepo.save(tempLoanEntity);
        }
    }
//
    @Override
    public LoanDto inqLoanByMobile (String mobileNumber)
    {   Long tempLoanID;
        Optional<LoanEntity> loanEntityOptional = loansRepo.findBymobileNumber(mobileNumber);
           if (loanEntityOptional.isPresent())
           {   LoanEntity loanEntity=getLoanEntity(loanEntityOptional);
               return LoanMapper.mapLoanToDto(new LoanDto(), loanEntity);
           }   else {throw new ResourceNotFoundException("LoanEntity", "LoanNumber", mobileNumber);}
    }
    @Override
    public boolean updLoanDetails(LoanDto loanDto) {
          Long tempLoanID;
          if (loanDto != null)
          {
              Optional<LoanEntity> loanEntityOptional = loansRepo.findBymobileNumber(loanDto.getMobileNumber());
              if (loanEntityOptional.isPresent())
              {   LoanEntity loanEntity=getLoanEntity(loanEntityOptional);
                  loansRepo.save(LoanMapper.mapDtoToLoan(loanDto, loanEntity)); return true;}
              else { throw new ResourceNotFoundException("LoanEntity", "LoanNumber", loanDto.getMobileNumber());}
          }
          else
          { throw new ResourceNotFoundException("LoanEntity", "LoanNumber", " No input");}
    }

    @Override
    public boolean delLoanDetails(String mobileNumber)
    {   Long tempLoanID;
        Optional<LoanEntity> loanEntityOptional = loansRepo.findBymobileNumber(mobileNumber);
        if (loanEntityOptional.isPresent()) {
            LoanEntity loanEntity=getLoanEntity(loanEntityOptional);
            loansRepo.deleteById(loanEntity.getLoanID()); return true;
        } else{ throw new ResourceNotFoundException("LoanEntity", "LoanNumber", mobileNumber);}
    }
}
