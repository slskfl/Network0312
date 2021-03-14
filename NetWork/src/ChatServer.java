import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends Thread{
	private InputStream is; //C->S
	private OutputStream os; //S->C
	private ObjectInputStream ois; //�޴� �ٱ���st
	private ObjectOutputStream oos; //������ �ٱ���st
	private ServerSocket serverSocket; //���� ���� (��Ʈ��ũ ���� ��ġ)
	Socket socket; //Ŭ���̾�Ʈ ����
	public void run() { //������ �޼���
		try {
			serverSocket=new ServerSocket(7000);
			System.out.println("��û ���");
			while(true) {
				//������ ��� �����־����. (��û ���)
				socket=serverSocket.accept();
				System.out.println("������ Ŭ���̾�Ʈ : " + socket.getInetAddress());
				is=socket.getInputStream();
				ois=new ObjectInputStream(is);
				os=socket.getOutputStream();
				oos=new ObjectOutputStream(os);
				String msg=(String)ois.readObject(); //String���� �� ��ȯ �ʿ�
				System.out.println(msg);
				oos.writeObject(msg);
				socket.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public static void main(String[] args) {
		new ChatServer().start();
	}
}
