package com.msandy.loans.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Schema(name = "Error Details", description = "Schema to return Error information ")
public class RespErrDto {
    @Schema(name = "apiPath", description = "API path, Schema to reveal which api the error occurred")
    private String apiPath;
    @Schema(name = "errCode", description = "Error Code, Field to return error code information")
    private HttpStatus errCode;
    @Schema(name = "errMsg", description = "Error Message, Field to provide specific error message")
    private String errMsg;
    @Schema(name = "errTime", description = "Error TimeStamp, Field to provide the time of the error occurred")
    private LocalDateTime errTime;
}

