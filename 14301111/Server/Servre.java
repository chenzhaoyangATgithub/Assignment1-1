package Server;
import java.io.*;
import java.net.*;

public class Servre{
	
	public static void main(String[] args){
		try{
			ServerSocket serverSocket = new ServerSocket(3333);
			Socket socket = null;
			//��¼�ͻ��˵�����
			int count = 0;
			System.out.println("�����������������ȴ��ͻ�������");
			//ѭ����ͧ�ȴ��ͻ�������
			while(true){
				//����accept������ʼ��ͧ���ȴ��ͻ��˵�����
				socket = serverSocket.accept();
				//����һ���µ��߳�
				ServerThread serverThread = new ServerThread(socket);
				//�����߳�
				serverThread.start();
				
				count++;//ͳ�ƿͻ���������
				System.out.println("�ͻ��˵�������"+count);
				InetAddress address = socket.getInetAddress();
				System.out.println("��ǰ�ͻ�����IP �� " + address.getHostAddress());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}