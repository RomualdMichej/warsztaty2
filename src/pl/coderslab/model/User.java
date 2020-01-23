package pl.coderslab.model;


import org.mindrot.jbcrypt.BCrypt;

public class User {
        private int id;
        private String userName;
        private String email;
        private String password;
        private int users_group_id;

    public User(String userName, String email, String password, int users_group_id) {
        this.userName = userName;
        this.email = email;
        this.users_group_id = users_group_id;
        hashPassword(password);
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUser_group_id() { return users_group_id; }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
        hashPassword(password);
    }

    public void setUser_group_id(int user_group_id) { this.users_group_id = user_group_id; }

    @Override
    public String toString() {
        return "User{" +
                "id = " + id +
                ", userName = '" + userName + '\'' +
                ", email = '" + email + '\'' +
                ", password = '" + password + '\'' +
                ", users_group_id = " + users_group_id +
                '}';
    }
}
