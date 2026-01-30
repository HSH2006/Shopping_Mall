package Users;

public class Administrator extends User {

    public Administrator(String id, String username, String password, String email) {
        super(id, username, password, email, "Administrator");
    }
}
