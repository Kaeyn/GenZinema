package android.genzinema.Model;

import androidx.annotation.NonNull;

public class User {
    String email, password, phone, displayName;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public User() {
    }

    public User(String email, String password, String phone, String displayName) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.displayName = displayName;
    }
}
