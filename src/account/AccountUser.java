package account;

public class AccountUser {
    private String account,password;

    public AccountUser() {
    }

    public AccountUser(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
