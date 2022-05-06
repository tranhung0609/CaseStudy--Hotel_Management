package account;

import java.util.ArrayList;

public class AccountAdmin {
    private String adminAccount,adminPassWord;
    private final ArrayList<AccountAdmin> accountAdminList = new ArrayList<>();

    public AccountAdmin() {
    }

    public AccountAdmin(String adminAccount, String adminPassWord) {
        this.adminAccount = adminAccount;
        this.adminPassWord = adminPassWord;
    }

    public String getAdminAccount() {
        return adminAccount;
    }


    public String getAdminPassWord() {
        return adminPassWord;
    }

    public ArrayList<AccountAdmin> getAccountAdminList() {
        return accountAdminList;
    }

    public void setAdmin(String adminAccount,String adminPassWord){
        accountAdminList.add(new AccountAdmin(adminAccount,adminPassWord));
    }

    @Override
    public String toString() {
        return "AccountAdmin{" +
                "adminAccount='" + adminAccount + '\'' +
                ", adminPassWord='" + adminPassWord + '\'' +
                '}';
    }
}
