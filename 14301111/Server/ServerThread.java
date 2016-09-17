package Server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
	
	//�ͱ��߳���ص�socket
	Socket socket = null;
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	
	//�߳�ִ�еĲ�������Ӧ�ͻ��˵�����
	public void run(){
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		OutputStream os = null;
		PrintWriter pw = null;
		try{
			//��ȡһ����������ȥ��ȡ�ͻ��˵���Ϣ
			is = socket.getInputStream();
			isr = new InputStreamReader(is);//���ֽ���ת��Ϊ�ַ���
			br = new BufferedReader(isr);//��ӻ���
			String info = null;
			String buff = "";
			//ѭ����ȡ����
			while((info = br.readLine()) != null){
				buff += info;
				System.out.println("�յ��ͻ��˷������� "+ info);
			}
			String back = "";
			for (int i = buff.length()-1; i >= 0; i--) { // �ַ����±��0��ʼ������-1�������������Դӳ���-1��ʼ��0������
				back += buff.charAt(i);
			}
			
			socket.shutdownInput();//�ر�������
			
			//��ȡ���������Ӧ�ͻ��˵�����
			os = socket.getOutputStream();
			pw = new PrintWriter(os);//��װΪ��ӡ��
			pw.write(back);
			pw.flush();//���������
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			
			try{
				//�ر���Դ
				if(pw != null)
					pw.close();
				if(os != null)
					os.close();
				if(is != null)
					is.close();
				if(isr != null)
					isr.close();
				if(br != null)
					br.close();
				if(socket != null)
					socket.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}














