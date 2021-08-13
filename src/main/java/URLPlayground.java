import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class URLPlayground {
    public static void main(String[] args) throws IOException {
        URL yandexUrl = new URL("http://yandex.ru");
        HttpURLConnection connection = (HttpURLConnection) yandexUrl.openConnection();
        System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());

        try (InputStream in = new BufferedInputStream(connection.getInputStream())) {
            int i;
            while ((i = in.read()) != -1) {
                System.out.print((char) i);
            }
        } finally {
            connection.disconnect();
        }

        System.out.println("\n Заголовки: ");

        for (Map.Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
            System.out.println(header.getKey() + " = " + header.getValue());
        }

    }
}
