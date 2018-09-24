package com.J621.utils;

import java.io.File;

public class SysUtils {
	
	

	public static boolean deletePath(String userId) {
        StringBuffer path = new StringBuffer();
        if(FinalStrings.getOS().contains("win")) {
        	 path.append(FinalStrings.WINADDR);
        }else {
        	path.append(FinalStrings.LINADDR);
        }
        path.append(File.separator);
        path.append(userId);
        File file = new File(path.toString());
        System.out.println("路径："+ path.toString());
        //方法一
        /*try {
            org.apache.commons.io.FileUtils.deleteDirectory(file);
            return true;
        } catch (IOException e) {
            System.out.println("删除异常！");
            return false;
        }*/
        //方法二
        return deleteDir(file);
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * 
     * @param dir
     *            将要删除的文件目录
     * @return
     */
    private static boolean deleteDir(File dir) {
        if (!dir.exists()) return false;
        if (dir.isDirectory()) {
            String[] childrens = dir.list();
            // 递归删除目录中的子目录下
            for (String child : childrens) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) return false;
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    
    public static void main(String[] args) {

        if (deletePath("管理员")) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
 
}
