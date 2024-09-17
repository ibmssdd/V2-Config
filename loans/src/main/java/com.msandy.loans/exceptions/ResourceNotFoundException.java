package com.msandy.loans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
    String stringResourceNotFound="Resource Not Found";
    public ResourceNotFoundException(String rName, String fName, String fValue)
    {
//      super(String.format("Resource %s not found for field= %s with value= %s, rName, fName, fValue"));
        super(String.format("%s not found with the given input data %s : '%s'", rName, fName, fValue));
//        super(stringResourceNotFound);
    }
}
