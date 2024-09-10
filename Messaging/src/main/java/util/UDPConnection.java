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
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void sendDatagram(String msj, String ipDest, int portDest){
        try {
            InetAddress ipAddress = InetAddress.getByName(ipDest);
            DatagramPacket packet = new DatagramPacket(msj.getBytes(), msj.length(), ipAddress, portDest);
            socket.send(packet);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para recibir un datagrama
    public String receiveDatagram() {
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