import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.net.httpserver.HttpServer;

public class Servidor {

    int visitas = 0;

    public void iniciar() throws Exception {

        HttpServer servidor = HttpServer.create(new InetSocketAddress(8080), 0);

        servidor.createContext("/", troca -> {

            visitas++;

            String html = Files.readString(Path.of("index.html"));

            html = html.replace("<!-- VISITAS -->", "Visitas: " + visitas);
            html = html.replace("<!-- HORA -->", "Hora: " + java.time.LocalTime.now());

            troca.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");

            byte[] resposta = html.getBytes("UTF-8");

            troca.sendResponseHeaders(200, resposta.length);

            troca.getResponseBody().write(resposta);

            troca.close();
        });

        servidor.start();

        System.out.println("Servidor rodando na porta 8080");

        Thread.currentThread().join();
    }
}