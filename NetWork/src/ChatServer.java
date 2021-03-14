import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends Thread{
	private InputStream is; //C->S
	private OutputStream os; //S->C
	private ObjectInputStream ois; //받는 바구니st
	private ObjectOutputStream oos; //보내는 바구니st
	private ServerSocket serverSocket; //서버 소켓 (네트워크 연결 장치)
	Socket socket; //클라이언트 소켓
	public void run() { //스레드 메서드
		try {
			serverSocket=new ServerSocket(7000);
			System.out.println("요청 대기");
			while(true) {
				//서버는 계속 켜져있어야함. (요청 대기)
				socket=serverSocket.accept();
				System.out.println("접속한 클라이언트 : " + socket.getInetAddress());
				is=socket.getInputStream();
				ois=new ObjectInputStream(is);
				os=socket.getOutputStream();
				oos=new ObjectOutputStream(os);
				String msg=(String)ois.readObject(); //String으로 형 변환 필요
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
