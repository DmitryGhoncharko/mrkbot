package cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class UsersCache {
    private static final List<String> USERS = new ArrayList<>();

    static {
        USERS.add("dangeonMaster21");
        USERS.add("eblankoroche");
        USERS.add("cryflystbt");
        USERS.add("hachapury18");
        USERS.add("Tapokminzk");
        USERS.add("Vkheych");
        USERS.add("polkash_a_d");
        USERS.add("sheehiteo");
        USERS.add("nonamejavot");
        USERS.add("Stitch_2001");
        USERS.add("yliiinkaaa");
        USERS.add("Iloveplovw");
        USERS.add("AlinaBatsko");
    }
    public static boolean addUser(String userName) {
        return USERS.add(userName);
    }

    public static Optional<String> userIsPresent(String userName) {
        return USERS.stream().filter(x -> x.equals(userName)).findFirst();
    }
    public static int getCacheSize(){
        return USERS.size();
    }
    public static String getUserNameByIndex(int index){
        return USERS.get(index);
    }
}
