package util;

import java.io.IOException;
import java.net.*;

public class UDPConnection extends Thread {

    private DatagramSocket socket;
    private int port;

    private static UDPConnection instance;

    private UDPConnection() {}

    public static UDPConnection getInstance(){
        if(instance == null){
            instance = new UDPConnection();
        }
        return instance;
    }

    public void setPort(int port){
        this.port = port;
        try {
            if (socket == null || socket.isClosed()) {  // Verifica si el socket ya fue creado o está cerrado
                this.socket = new DatagramSocket(port);
            }
        } catch (SocketException e) {
            e.printStackTrace();
            System.out.println("Error creando el socket en el puerto: " + port);
        }
    }

    public void sendDatagram(String msj, String ipDest, int portDest){
        if (socket == null) {
            System.out.println("El socket no está inicializado. Por favor, establece el puerto antes de enviar mensajes.");
            return;
        }

        try {
            InetAddress ipAddress = InetAddress.getByName(ipDest);
            DatagramPacket packet = new DatagramPacket(msj.getBytes(), msj.length(), ipAddress, portDest);
            socket.send(packet);
        } catch (UnknownHostException e) {
            System.out.println("Dirección IP no válida: " + ipDest);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error enviando el datagrama.");
        }
    }

    // Método para recibir un datagrama
    public String receiveDatagram() {
        if (socket == null) {
            System.out.println("El socket no está inicializado. No se puede recibir mensajes.");
            return null;
        }

        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            return new String(packet.getData(), 0, packet.getLength());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}