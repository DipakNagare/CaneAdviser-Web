package com.cdac.caneadviser.dao;

import javax.validation.constraints.NotNull;

public class VerifyOTP {

    @NotNull(message = "Mobile Number is required")
    private String mobileno;

    @NotNull(message = "OTP is required")
    private String otp;

    @NotNull(message = "IMEI is required")
    private String imei;

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}