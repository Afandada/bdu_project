package com.schoolms.controller;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;


@CrossOrigin
@RestController
@RequestMapping("/yulan")
public class YulanController {
	
	// 将word格式的文件转换为pdf格式
    public static void WordToPDF(String startFile,String overFile) throws IOException {
        // 源文件目录
        File inputFile = new File(startFile);
        if (!inputFile.exists()) {
            System.out.println("源文件不存在！");
            return;
        }
         
        // 输出文件目录
        File outputFile = new File(overFile);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().exists();
        }
        
        // 调用openoffice服务线程
        /** 我把openOffice下载到了 C:/Program Files (x86)/下  ,下面的写法自己修改编辑就可以**/
        String command = "C:/Program Files (x86)/OpenOffice 4/program/soffice.exe -headless -accept=\"socket,host=0.0.0.0,port=8100;urp;\"";
        Process p = Runtime.getRuntime().exec(command);
 
        //连接openoffice服务
        //OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
      OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
       connection.connect();
 
        // 转换
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
      
        converter.convert(inputFile, outputFile);
 
        // 关闭连接
        connection.disconnect();
 
        // 关闭进程
        p.destroy();
    }

    @ResponseBody
	@CrossOrigin
	@PostMapping(value="/yl",produces = {"text/plain;charset=UTF-8"})
	public  String wordToPDF1( @RequestBody  String filepath) throws UnsupportedEncodingException{    
    	//到时候要记录一下，前端使用encodeURI（)加密。后台使用URLDecoder.decode()解密
    //使用urldecoder,decode()后，也要使用接下来的一句
    	String start1=URLDecoder.decode(filepath,"UTF-8");
    	String name=new String(start1.getBytes("ISO-8859-1"),"utf-8");
    	//String start1=filepath;
    	//替换\为\\       //String start = "F:\\packages\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\schoolms\\upload\\3d打印过程.docx";
        String start2=name.replaceAll("/","\\\\");
        //要想使用replaceAll()方法将字符串中的反斜杠(\)替换成(\\)，replace有返回值则需要这样写：replaceAll("\\\\","\\\\\\\\")
        String start3=start2.replaceAll("\\\\","\\\\\\\\");
        String start=start3.replaceAll("=","");
        
        System.out.println(start);
        String over = "F:\\packages\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\schoolms\\upload\\文件预览.pdf";
        try {
        	WordToPDF(start,over);
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        System.out.println(over);
        return  "http://localhost:8888/schoolms/upload/文件预览.pdf";
       //String over1="F:\\packages\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\schoolms\\upload\\文件预览.pdf";
       // return over1;
	}

	
	/*
	@Autowired
	private static final int wdFormatPDF = 17;// PDF 格式    
	@SuppressWarnings("restriction")
	@ResponseBody
	@CrossOrigin
	@PostMapping(value="/yl",produces = {"text/plain;charset=UTF-8"})
	public  String wordToPDF( @RequestBody  String filepath){    
     System.out.println(filepath+"dssssssssffdddddddddddddddddd");
		ActiveXComponent app = null;  
		Dispatch doc = null;  
		 String overFile =  "F:\\新建文件夹\\我是转换后的pdf文件"  + ".pdf";
		try {      
			app = new ActiveXComponent("Word.Application");      
			app.setProperty("Visible", new Variant(false));  
			Dispatch docs = app.getProperty("Documents").toDispatch();    
 
			//转换前的文件路径
			String startFile = filepath;
			//转换后的文件路劲
			overFile =  "F:\\新建文件夹\\我是转换后的pdf文件"  + ".pdf";
 
			doc = Dispatch.call(docs,  "Open" , startFile).toDispatch();  
			File tofile = new File(overFile);      
			if (tofile.exists()) {      
				tofile.delete();      
			}      
			Dispatch.call(doc,"SaveAs", overFile, wdFormatPDF);     
			System.out.println("****222222************######***"+overFile);
		} catch (Exception e) {      
			System.out.println(e.getMessage());      
		} finally {  
			Dispatch.call(doc,"Close",false);  
			if (app != null)      
				app.invoke("Quit", new Variant[] {});   
			
		}  
		//结束后关闭进程   
		System.out.println("****************######***"+overFile);
		ComThread.Release();
		return overFile;
		
		
	}*/
	
    }
    

 
 

