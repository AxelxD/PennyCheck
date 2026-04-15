package com.compartamos.pennycheck.mapper;

import com.compartamos.pennycheck.dto.AccountValidatorRequest;
import com.compartamos.pennycheck.model.CircleCreditRequest;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PennyCheckMapper {

    public CircleCreditRequest toCircleCreditRequest(AccountValidatorRequest request) {
        CircleCreditRequest target = new CircleCreditRequest();
        target.accountValidator = request.accountValidator;
        return target;
    }
}
