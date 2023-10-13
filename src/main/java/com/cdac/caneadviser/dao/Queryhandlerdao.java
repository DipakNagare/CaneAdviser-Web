package com.cdac.caneadviser.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

public class Queryhandlerdao {

    @NotNull(message = "Farm ID is required")
    @Pattern(regexp = "\\d+", message = "Farm ID must contain only numbers")
    private String farmId;

    @NotNull(message = "Image 1 is required")
    private MultipartFile image1;

    private MultipartFile image2;
    private MultipartFile image3;

    @NotNull(message = "{query.required}")
    @Pattern(regexp = "^[A-Za-z0-9_/. ]+$", message = "{query.invalid}")
    private String query;

    public String getFarmId() {
        return farmId;
    }

    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    public MultipartFile getImage1() {
        return image1;
    }

    public void setImage1(MultipartFile image1) {
        this.image1 = image1;
    }

    public MultipartFile getImage2() {
        return image2;
    }

    public void setImage2(MultipartFile image2) {
        this.image2 = image2;
    }

    public MultipartFile getImage3() {
        return image3;
    }

    public void setImage3(MultipartFile image3) {
        this.image3 = image3;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
