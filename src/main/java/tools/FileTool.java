package tools;

import java.io.File;

public class FileTool {
	public static String root;
	
	//获取完整路径中文件名的路径
	public static String getDictionary(String fullFileName){
		int index=fullFileName.lastIndexOf("\\");
		return fullFileName.substring(0, index);
	}
	//获取完整路径中的文件名
	public static String getFileName(String fullFileName){
		int index=fullFileName.lastIndexOf("\\");
		return fullFileName.substring(index+1);
	}
	//获取不带路径的文件拓展名
	public static String getExtendedFileName(String fileName){
		int index=fileName.lastIndexOf(".");
		return fileName.substring(index+1);
	}
	//根据完整路径生成随机文件名（文件名为当前时间的整数值），拓展名不变
	public static String getRandomFileNameByCurrentTime(String fileName){
		String randomFileName=System.currentTimeMillis()+"."+getExtendedFileName(fileName);
		return randomFileName;
	}
	//根据完整路径生成随机文件名（文件名为当前时间的整数值），拓展名不变，路径不变
	public static String getRandomFileNameFromFullFilaNameByCurrentTime(String fullFileName){
		String randomFileName=System.currentTimeMillis()+"."+getExtendedFileName(getFileName(fullFileName));
		return getDictionary(fullFileName)+randomFileName;
	}
	//删除文件
	public static boolean deleteFile(File file){
		boolean result=false;
		int tryCount=0;
		while(!result&&tryCount++<10){
			result=file.delete();
			System.gc();
		}
		return result;
	}
	//删除文件夹
	private static boolean deleteDictionary(File file){
		boolean result=true;
		if(file.exists()){
			if(file.isFile()){
				if(file.delete()==false){
					result=false;
				}
			}else if(file.isDirectory()){
				File files[]=file.listFiles();
				for(int i=0;i<files.length;i++){
					if(deleteDictionary(files[i])==false){//递归对目录下的所有文件再进行删除文件夹操作
						result=false;
					}
				}
				if(file.delete()==false){
					result=false;
				}
			}
		}
		return result;
	}
	
	
}
