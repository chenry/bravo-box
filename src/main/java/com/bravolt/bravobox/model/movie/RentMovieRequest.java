package com.bravolt.bravobox.model.movie;

public class RentMovieRequest {

  private String imdbId;
  private String creditCardNumber;
  private String expirationDate;
  private String zipCode;
  private String emailAddress;

  public String getImdbId() {
    return imdbId;
  }

  public void setImdbId(String imdbId) {
    this.imdbId = imdbId;
  }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public String getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  @Override
  public String toString() {
    return "RentMovieRequest [imdbId=" + imdbId + ", creditCardNumber=" + creditCardNumber
        + ", expirationDate=" + expirationDate + ", zipCode=" + zipCode + ", emailAddress="
        + emailAddress + "]";
  }

}
