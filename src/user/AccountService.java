package user;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private Map<String, String> userData = new HashMap<>();

    public Map<String, String> getUserData() {
        return userData;
    }
}
