package com.msandy.loans.dtos;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Schema(name = "Response Format", description = "Schema to response information")
public class RespDto
{
    @Schema(name = "statusCode", description = "Response Code field, value examples 200, 404, 500 etc")
    private String statusCode;
    @Schema(name = "statusMsg", description = "Response Message field, value example Request Successful, Page not found etc.")
    private String statusMsg;
}
