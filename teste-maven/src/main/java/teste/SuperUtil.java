package teste;
import org.apache.commons.lang3.StringEscapeUtils;

public class SuperUtil {

    /**
     * Possibilita exibir um texto contendo HTML no navegador sem ataques XSS.
     * @param html Entrada do usuário (pode ter HTML, mas não deve ser renderizado, somente exibido)
     * @return Texto sem posíveis tags HTML
     */
    public static String escapeHTML(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

}
