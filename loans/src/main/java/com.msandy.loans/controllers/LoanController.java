package com.msandy.loans.controllers;

import com.msandy.loans.constants.LoanConstants;
import com.msandy.loans.dtos.ContactDto;
import com.msandy.loans.dtos.LoanDto;
import com.msandy.loans.dtos.RespDto;
import com.msandy.loans.dtos.RespErrDto;
import com.msandy.loans.services.intf.IntfSvcLoan;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.lang.String;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(  name="CRUD REST APIs for Loans in SandyZ App",
       description = "CRUD REST APIs in SandyZ app to CREATE, UPDATE, FETCH AND DELETE loan details")

@RestController
@RequestMapping(path="/api",produces={MediaType.APPLICATION_JSON_VALUE})
@Validated
@Configuration

public class LoanController {
        private final IntfSvcLoan intfSvcLoan;
        public LoanController(IntfSvcLoan intfSvcLoan) { this.intfSvcLoan=intfSvcLoan; }
        @Value("${build.version}")
        private String buildVersion;

        @Autowired
        private Environment environment0;
        @Autowired
        private Environment environment1;
        @Autowired
        private ContactDto contactDto;

    // defining default constructor.
//*=====================================================================
//* --------------- API to Create New Loan - Code Begins ---------------
    @Operation(summary = "Create Loan REST API",description = "REST API to create new loan")
    @ApiResponses({@ApiResponse(responseCode="201",description="HTTP Status CREATED"),
                   @ApiResponse(responseCode="500",description="HTTP Status Internal Server Error",
                   content = @Content(schema=@Schema(implementation=RespErrDto.class)))
                 })
    @PostMapping("/create")
    public ResponseEntity<RespDto> createNewLoan(@Valid
                                             @RequestParam
                                             @Pattern(regexp="(^|[0-9]{10})",message = "Mobile number must be 10 digit")
                                             String mobileNumber)
    {   intfSvcLoan.createNewLoan(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(new RespDto(LoanConstants.STATUS_201,LoanConstants.MESSAGE_201));
    }
//* --------------- API to Create New Loan - Code Ends ---------------

//*=================================================================================
//* --------------- API to Fetch Loan by mobile number - Code Begins ---------------
    @GetMapping("/fetch")
    @Operation(summary = "Loan Inquiry (By Mobile) REST API",description="REST API to inquiry loan using mobile Number")
    @ApiResponses({@ApiResponse(responseCode="200",description="HTTP Status Successful"),
            @ApiResponse(responseCode="500",description="HTTP Status Internal Server Error",
                    content = @Content(schema=@Schema(implementation=RespErrDto.class)))})

    public ResponseEntity<LoanDto> inqLoanByMobile(@Valid @RequestParam
                                        @Pattern(regexp="(^|[0-9]{10})",message = "Mobile number must be 10 digit")
                                        String mobileNumber)
    {   LoanDto loanDto=intfSvcLoan.inqLoanByMobile(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loanDto);
//*                             .body(loanDto, new RespDto(LoanConstants.STATUS_200,LoanConstants.STATUS_200));
    }

//* --------------- API to Fetch Loan by mobile number - Code Ends ---------------

//* --------------- API to Update Loan details / check body data, identify loan by mobile number - Code Begins ---------------
    @PutMapping("/update")
    @Operation(summary="Update Loan Details REST API",description="REST API to update loan details by loan number")
    @ApiResponses({@ApiResponse(responseCode="200",description="HTTP Status Successful"),
                   @ApiResponse(responseCode="417",description="Loan Update Failed"),
                   @ApiResponse(responseCode="500",description="HTTP Status Internal Server Error",
                            content = @Content(schema=@Schema(implementation=RespErrDto.class)))})
    public ResponseEntity<RespDto> UpdateLoanDetails(@RequestBody LoanDto loanDto)
    {
        boolean LoanUpdateOk = intfSvcLoan.updLoanDetails(loanDto);
        if (LoanUpdateOk)
           {return ResponseEntity.status(HttpStatus.OK)
                                 .body(new RespDto(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200));
           }
        else
            {return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                                  .body(new RespDto(LoanConstants.STATUS_417,LoanConstants.MESSAGE_417_UPDATE));
            }
    }
//* --------------- API to Fetch Loan by mobile number - Code Ends ---------------

//* --------------- API to Update Loan details / check body data, identify loan by mobile number - Code Begins ---------------
@PutMapping("/delete")
@Operation(summary="Delete Loan Data REST API",description="REST API to delete loan details based on mobile number")
@ApiResponses({@ApiResponse(responseCode="200",description="HTTP Status Successful"),
                @ApiResponse(responseCode="417",description="Loan Deletion Failed"),
                @ApiResponse(responseCode="500",description="HTTP Status Internal Server Error",
                        content = @Content(schema=@Schema(implementation=RespErrDto.class)))})
    public ResponseEntity<RespDto> deleteLoanDetails(@Valid
                                                 @RequestParam
                                                 @Pattern(regexp="(^|[0-9]{10})",message="Mobile # must be 10 digit")
                                                 String mobileNumber)
    {   boolean loanDeletedOk = intfSvcLoan.delLoanDetails(mobileNumber);
        if(loanDeletedOk)
            {return ResponseEntity.status(HttpStatus.OK)
                                  .body(new RespDto(LoanConstants.STATUS_200,LoanConstants.MESSAGE_200));
            }
        else
            {return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                                  .body(new RespDto(LoanConstants.STATUS_417,LoanConstants.MESSAGE_417_DELETE));
            }
        }

    @GetMapping("/build-info")
    @Operation(summary = "Obtaining Build version information",description="REST API to get buildVersion")
    @ApiResponses({@ApiResponse(responseCode="200",description="HTTP Status Successful"),
            @ApiResponse(responseCode="500",description="HTTP Status Internal Server Error",
                    content = @Content(schema=@Schema(implementation=RespErrDto.class)))})
      public ResponseEntity<String> getBuildinfo()
    { return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }
    @GetMapping("/java-version")
    @Operation(summary = "Obtaining Java version information",description="REST API to get Java Version")
    @ApiResponses({@ApiResponse(responseCode="200",description="HTTP Status Successful"),
            @ApiResponse(responseCode="500",description="HTTP Status Internal Server Error",
                    content = @Content(schema=@Schema(implementation=RespErrDto.class)))})
    public ResponseEntity<String> getJavaVersion()
    { return ResponseEntity.status(HttpStatus.OK).body(environment0.getProperty("JAVA_HOME"));
    }

    @GetMapping("/mvn-info")
    @Operation(summary = "Obtaining maven information",description="REST API to get maven info")
    @ApiResponses({@ApiResponse(responseCode="200",description="HTTP Status Successful"),
            @ApiResponse(responseCode="500",description="HTTP Status Internal Server Error",
                    content = @Content(schema=@Schema(implementation=RespErrDto.class)))})
    public ResponseEntity<String> getMavenInfo()
    { return ResponseEntity.status(HttpStatus.OK).body(environment1.getProperty("MAVEN_HOME"));

    }
    @Operation(summary = "Obtaining Developer Contact information",description="REST API to developer info")
    @ApiResponses({@ApiResponse(responseCode="200",description="HTTP Status Successful"),
            @ApiResponse(responseCode="500",description="HTTP Status Internal Server Error",
                    content = @Content(schema=@Schema(implementation=RespErrDto.class)))})
    @GetMapping("/contact-info")
    public ResponseEntity<ContactDto> getContactInfo()
    {
        return ResponseEntity.status(HttpStatus.OK).body(contactDto);
    }
}