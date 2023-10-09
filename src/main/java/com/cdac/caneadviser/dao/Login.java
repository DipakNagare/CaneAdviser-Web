package com.cdac.caneadviser.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class Login {

    @NotNull(message = "Mobile Number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile Number should be exactly 10 digits")
    private String mobileno;

    @NotNull(message = "IMEI Number is required")
    private String imeino;

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getImeino() {
        return imeino;
    }

    public void setImeino(String imeino) {
        this.imeino = imeino;
    }
}
