import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Servidor {

    int visitas = 0;

    public void iniciar() throws Exception {

        HttpServer servidor = HttpServer.create( new InetSocketAddress(8080), 0 );

        servidor.createContext("/", troca -> {

            visitas++;

            String hora = java.time.LocalTime.now().toString();

            String html = """
                <html>
                    <body>
                        <h1>Meu Site</h1>

                        <p>Visitas: %d</p>

                        <p>Hora: %s</p>
                    </body>
                </html>
                """.formatted(visitas, hora);

            troca.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");

            troca.sendResponseHeaders(200, html.getBytes("UTF-8").length);

            troca.getResponseBody().write(html.getBytes("UTF-8"));

            troca.close();
        });

        servidor.start();
java.awt.Desktop.getDesktop().browse(new java.net.URI("http://localhost:8080"));
        System.out.println("Servidor rodando em http://localhost:8080");

        Thread.currentThread().join();
    }
}