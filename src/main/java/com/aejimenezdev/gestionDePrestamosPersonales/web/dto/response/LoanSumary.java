package com.aejimenezdev.gestionDePrestamosPersonales.web.dto.response;

import java.math.BigDecimal;

public interface LoanSumary {
    void setPerDay(boolean perDay);
    void setCurrentMonthInterest(BigDecimal amount);
    void setOutstandingBalance(BigDecimal amount);
    void setPrincipalOutstanding(BigDecimal amount);
    void setTotalPaidAmount(BigDecimal amount);
}
