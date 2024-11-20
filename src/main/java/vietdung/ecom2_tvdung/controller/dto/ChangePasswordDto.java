package vietdung.ecom2_tvdung.controller.dto;

public class ChangePasswordDto {
    private String email;
    private String currPassword;
    private String newPassword;
    private String confirmPassword;
    public ChangePasswordDto() {
    }
    public ChangePasswordDto(String email, String currPassword, String newPassword, String confirmPassword) {
        super();
        this.email = email;
        this.currPassword = currPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
    public String getCurrPassword() {
        return currPassword;
    }
    public void setCurrPassword(String currPassword) {
        this.currPassword = currPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    
}
