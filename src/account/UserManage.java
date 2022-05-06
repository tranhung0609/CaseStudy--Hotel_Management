package account;

import write_read_File.IOFile;

import java.io.File;
import java.util.ArrayList;

public class UserManage {
    private ArrayList<User> userList;
    private final IOFile<User>ioFile = new IOFile<>();
    private final String PATHNAME_OF_USER = "src\\filedata\\userinfor";

    public UserManage(){
        if (new File(PATHNAME_OF_USER).length()==0){
            this.userList = new ArrayList<>();
        } else {
            this.userList = ioFile.readFile(PATHNAME_OF_USER);
        }
    }

    public ArrayList<User> getUserList(){
        return userList;
    }

    public void displayUserList(){
        userList = ioFile.readFile(PATHNAME_OF_USER);
        if (userList.isEmpty()) {
            System.out.println("Chưa có người dùng nào đăng ký !!!");
            System.out.println("--------------------");
        } else {
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("| %-15s| %-15s| %-15s| %-15s |\n", "Tên", "Tài khoản", "Tuổi", "Số điện thoại");
            System.out.println("----------------------------------------------------------------------");
            for (User user : userList) {
                System.out.printf("| %-15s| %-15s| %-15d| %-15s |\n", user.getName(), user.getAccount(), user.getAge(), user.getPhoneNumber());
                System.out.println("----------------------------------------------------------------------");
            }
        }
    }

    public void
}
