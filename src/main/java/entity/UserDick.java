package entity;

import java.util.Objects;

public class UserDick {
    private final String userName;
    private final String dickMessage;

    public UserDick(String userName, String dickMessage) {
        this.userName = userName;
        this.dickMessage = dickMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDick userDick = (UserDick) o;
        return Objects.equals(userName, userDick.userName) && Objects.equals(dickMessage, userDick.dickMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, dickMessage);
    }

    @Override
    public String toString() {
        return "UserDick{" +
                "userName='" + userName + '\'' +
                ", dickMessage='" + dickMessage + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public String getDickMessage() {
        return dickMessage;
    }
}
