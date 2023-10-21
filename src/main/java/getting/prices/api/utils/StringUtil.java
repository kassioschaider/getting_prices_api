package getting.prices.api.utils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static BigDecimal extractPrice(String price, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(price);

        if (matcher.find()) {
            return new BigDecimal(matcher.group(1).replace(",", "."));
        }

        //TODO resolver o fato do método depender do usuário inserir um grupo no pattern (group(1))
        throw new RuntimeException("Error on try extractPrice with: Price=" + price + ", and Regex=" + regex );
    }
}
