import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import javax.imageio.ImageIO;

public class ImageClient {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Image Viewer");
        JLabel label = new JLabel();
        frame.add(label);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 9999);
                 InputStream in = socket.getInputStream()) {
                while (true) {
                    byte[] sizeBytes = in.readNBytes(4);
                    int size = ((sizeBytes[0] & 0xFF) << 24) |
                               ((sizeBytes[1] & 0xFF) << 16) |
                               ((sizeBytes[2] & 0xFF) << 8) |
                               (sizeBytes[3] & 0xFF);
                    byte[] imageBytes = in.readNBytes(size);
                    ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                    BufferedImage img = ImageIO.read(bais);
                    if (img != null) {
                        ImageIcon icon = new ImageIcon(img);
                        SwingUtilities.invokeLater(() -> label.setIcon(icon));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
