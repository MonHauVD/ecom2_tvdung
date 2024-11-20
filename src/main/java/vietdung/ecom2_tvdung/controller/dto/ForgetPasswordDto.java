package vietdung.ecom2_tvdung.controller.dto;

public class ForgetPasswordDto {
    private String email;
    private String newPassword;
    public ForgetPasswordDto() {
    }
    public ForgetPasswordDto(String email, String newPassword) {
        super();
        this.email = email;
        this.newPassword = newPassword;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    
    
}
