package ftptest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

public class FtpUtil {
	private String ip="192.68.81.210";
	private int port=21;
	private String username="test";
	private String passwd="zhirubin";
	private FTPClient ftpClient=new FTPClient();
	
	public void inital() {
		try {
			ftpClient.connect(ip,port);
			ftpClient.login(username, passwd);
			ftpClient.setType(FTPClient.TYPE_BINARY);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	

	private void upload(String serverdir,String localpath) {
		File file=new File(localpath);
		/*
		 * 判断服务器上有没有serverdir目录
		 * 没有的话新建
		 */
		try {
			FTPFile[] list=ftpClient.list();
			int i;
			for (i=0;i<list.length;i++) {
				if(list[i].getType()==list[i].TYPE_DIRECTORY){
					if(list[i].getName().equals(serverdir)){
						ftpClient.changeDirectory(serverdir);
						System.out.println("目录已经存在");
						break;
					}
				}
			}
			if(i==list.length){
				ftpClient.createDirectory(serverdir);
				ftpClient.changeDirectory(serverdir);
				System.out.println("新建目录成功");
			}
			//上传文件
			ftpClient.upload(file);
			System.out.println("上传成功");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FtpUtil ftpDemo=new FtpUtil();
		ftpDemo.inital();
		ftpDemo.upload("ftp4","E:\\html\\jquery-3.1.1.min.js");
	}
	
}
