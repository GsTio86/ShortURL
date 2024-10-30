package me.gt.shorturl.util;

import me.gt.shorturl.lib.MurmurHash3;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLShortUtils {

    private static final String URL_REGEX = "\\b(https?:\\/\\/|www\\.)((([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}|localhost)|((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])|\\[[0-9a-fA-F:.]+\\])(?::\\d{1,5})?(\\/[a-zA-Z0-9-._~%!$&'()*+,;=:@/]*)?(?:\\?[a-zA-Z0-9-._~%!$&'()*+,;=:@/?]*)?(?:#[a-zA-Z0-9-._~%!$&'()*+,;=:@/?]*)?";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
    private static final String CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    /*
     * 產生短網址
     * 會先將網址轉換成 byte[]，再透過MurmurHash來計算hash值
     * @param longUrl 長網址
     * @return 短網址
     */
    public static String generateShortUrl(String longUrl) {
        String saltedUrl = longUrl + generateRandomSalt();
        byte[] urlBytes = saltedUrl.getBytes(StandardCharsets.UTF_8);
        int hash = MurmurHash3.murmurhash3_x86_32(urlBytes, 0, urlBytes.length, 0);

        String shortUrl = toBase62(hash);
        while (shortUrl.length() < 7) { // 避免壓縮後網址過短 從而造成碰撞問題
            shortUrl += CHAR.charAt(RANDOM.nextInt(CHAR.length()));
        }
        return shortUrl;
    }

    /*
     * 將數字轉換成62進位
     * @param value 數字 (hash值)
     * @return 62進位字串
     */
    private static String toBase62(int value) {
        StringBuilder shortCode = new StringBuilder();
        while (value > 0) {
            shortCode.append(CHAR.charAt(value % 62));
            value /= 62;
        }
        return shortCode.reverse().toString();
    }

    /*
     * 產生隨機Salt值
     * @return 隨機Salt值
     */
    private static String generateRandomSalt() {
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < 7; i++) {  // 產生7位隨機字串
            salt.append(CHAR.charAt(RANDOM.nextInt(CHAR.length())));
        }
        return salt.toString();
    }

    /*
     * 驗證網址格式
     * @param url 網址
     * @return 是否為合法網址
     */
    public static boolean isValidUrl(String url) {
        return URL_PATTERN.matcher(url).matches();
    }

}
