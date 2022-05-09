package account;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountAdmin implements Serializable {
    private String adminAcc, adminPass;
    private final ArrayList<AccountAdmin> accountAdminList = new ArrayList<>();

    public AccountAdmin(String adminAcc, String adminPass) {
        this.adminAcc = adminAcc;
        this.adminPass = adminPass;
    }

    public AccountAdmin() {
        accountAdminList.add(new AccountAdmin("admin", "admin"));
        accountAdminList.add(new AccountAdmin("admin1", "admin1"));
        accountAdminList.add(new AccountAdmin("admin2", "admin2"));
    }

    public String getAdminAcc() {
        return adminAcc;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdmin(String adminAcc, String adminPass) {
        accountAdminList.add(new AccountAdmin(adminAcc, adminPass));
    }

    public ArrayList<AccountAdmin> getAccountAdminList() {
        return accountAdminList;
    }

    @Override
    public String toString() {
        return "AccountAdmin: " +
                "adminAcc = '" + adminAcc + '\'' +
                ", adminPass = '" + adminPass + '\'';
    }
}