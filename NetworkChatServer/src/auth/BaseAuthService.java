package auth;

import java.util.ArrayList;
import java.util.List;


public class BaseAuthService implements AuthService {

    private static class Entry {
        private String login;
        private String password;
        private String nick;

        public Entry(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }

    List<Entry> entries = new ArrayList<>();
            new Entry("login1", "pass1", "nick1"),
            new Entry("login2", "pass2", "nick2"),
            new Entry("login3", "pass3", "nick3")
    );

    @Override
    public void start() {
        System.out.println("Auth service is running");
    }

    @Override
    public void stop() {
        System.out.println("Auth service has stopped");
    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        for (Object entry : entries) {
            if (entry.login.equals(login) && entry.password.equals(pass)) {
                return entry.nick;
            }
        }

        return null;
    }
}
