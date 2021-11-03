import parser.RPandaLParser;

import java.io.IOException;
import java.net.PortUnreachableException;
import java.net.ServerSocket;

public class RPandaL {
    private static final int DEFAULT_PORT = 8080;
    private int port;
    private String host;

    public RPandaL(final String host, final int port) {
        this.host = host;
        try {
            if (port > 65536) {
                this.port = DEFAULT_PORT;
                throw new PortUnreachableException("Port higher than 65536");
            }
            this.port = port;
            System.out.println("Listening on port " + this.port);
        } catch (PortUnreachableException e) {
            System.err.println(e.getMessage());
        }
    }

    public int actualPort() {
        return this.port;
    }

    public static void main(String[] args) throws IOException {
        RPandaL multiThreadedRPandaL = new RPandaL("localhost", 20000);
       try (ServerSocket listener = new ServerSocket(multiThreadedRPandaL.actualPort())) {
           while (true) new Thread(new RPandaLParser(listener.accept())).start();
       }
    }
}
