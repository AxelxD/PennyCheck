package com.compartamos.pennycheck.mapper;

import com.compartamos.pennycheck.dto.PennyCheckRequest;
import com.compartamos.pennycheck.model.CircleCreditRequest;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PennyCheckMapper {

    public CircleCreditRequest toCircleCreditRequest(PennyCheckRequest request) {
        CircleCreditRequest target = new CircleCreditRequest();
        target.account_number = request.accountNumber;
        target.bank_code = request.bankCode;
        target.account_type = request.accountType;
        target.customer_id = request.customerId;
        target.request_timestamp = request.requestDate.toString();
        return target;
    }
}
