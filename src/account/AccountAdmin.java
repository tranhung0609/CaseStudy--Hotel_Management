package account;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountAdmin implements Serializable {
    private String adminAccount,adminPassWord;
    private final ArrayList<AccountAdmin> accountAdminList = new ArrayList<>();



    public AccountAdmin(String adminAccount, String adminPassWord) {
        this.adminAccount = adminAccount;
        this.adminPassWord = adminPassWord;
    }

    public AccountAdmin() {
        accountAdminList.add(new AccountAdmin("ADMIN1", "123456"));
        accountAdminList.add(new AccountAdmin("ADMIN2", "123456"));
        accountAdminList.add(new AccountAdmin("ADMIN3", "123456"));
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
