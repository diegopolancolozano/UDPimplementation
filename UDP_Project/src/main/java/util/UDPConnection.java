package util;

import java.io.IOException;
import java.net.*;

public class UDPConnection extends Thread {

    private DatagramSocket socket;
    private int port;

    private static UDPConnection instance;

    private String lastMessage;

    private UDPConnection() {
        lastMessage = "";
    }

    public static UDPConnection getInstance() {
        if (instance == null) {
            instance = new UDPConnection();
        }
        return instance;
    }

    public void setPort(int port) {
        this.port = port;
        try {
            if (this.socket != null && !this.socket.isClosed()) {
                this.socket.close();
            }
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        if (this.socket == null) {
            throw new IllegalStateException("Socket not initialized. Call setPort() before running.");
        }

        try {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Waiting for data...");
            while (true) {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + message);
                lastMessage = message;
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    public void sendDatagram(String message, String ipDest, int portDest) {
        if (this.socket == null || this.socket.isClosed()) {
            System.err.println("Socket is not initialized or is closed. Call setPort() to initialize it.");
            return;
        }

        try {
            InetAddress ipAddress = InetAddress.getByName(ipDest);
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), ipAddress, portDest);
            socket.send(packet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
