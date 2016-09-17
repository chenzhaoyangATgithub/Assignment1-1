package Client;
import java.io.*;
import java.net.*;

public class Client {
	private String name= "";
	public Client(){
		this.name = "Ĭ�ϻ�";
	} //ȱʡ����
	public Client(String name){
		this.name = name;
	}
	public void sentMessage(String msg){
		try{
			//�����ͻ���socket
			System.out.println("�ͻ���"+this.name+"��ʼ����");
			Socket socket = new Socket("127.0.0.1",3333);
			//��ȡ��������������������Ϣ
			OutputStream os = socket.getOutputStream();//�ֽ������
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.write(msg);
			System.out.println("������������ַ�����' "+ msg + " '");
			pw.flush();
			socket.shutdownOutput();//�ر������
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			
			String info = null;
			//ѭ����ȡ
			while((info = br.readLine()) != null){
				System.out.println("�յ������������ַ�����' " + info + " '");
			}
			System.out.println("�ͻ���"+this.name+"�������");
			br.close();
			is.close();
			isr.close();
			
			pw.close();
			os.close();
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new Client().sentMessage("123");
		
	}

}
