package com.jskno.myeazybankbackendapp.config;

import com.jskno.myeazybankbackendapp.model.Account;
import java.util.Objects;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
    implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }

    public boolean assetBelongToUser(Long customerId) {
        final Long loginCustomer = ((CustomPrincipal) this.getPrincipal()).getCustomerId();
        return Objects.equals(loginCustomer, customerId);
//        return whenDowncastToAccountWithCastMethod_thengetCustmerIdIsCalled(this.returnObject).equals(customerId);
    }

    public Long whenDowncastToAccountWithCastMethod_thengetCustmerIdIsCalled(Object returnObject) {
        if (Account.class.isInstance(returnObject)) {
            Account account = Account.class.cast(returnObject);
            return account.getCustomerId();
        }
        throw new IllegalArgumentException();
    }
}
