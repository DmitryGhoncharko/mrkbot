package sticker;

public enum Stickers {
    CUTE_CAT_STICKER("CAACAgIAAxkBAAEF9sZjN2HKdZ4K3v5BC-JpeMSpkMA-OwACphQAAujW4hLweTP2yeJAsyoE"),
    GACHI("CAACAgIAAxkBAAEF-3tjOcnFlF37IA4SaZ8d_yusRSqtNQACbxYAAg2pAUj2ZN0NwJMQICoE"),
    EBAT("CAACAgIAAxkBAAEF-39jOcyGKVHU4HJs_pv5hm3Zmr88mwACeB8AAlPPyEn0SPet4dYbUCoE");

    private final String stickerId;

    Stickers(String stickerId) {
        this.stickerId = stickerId;
    }

    public String getStickerId() {
        return stickerId;
    }
}
