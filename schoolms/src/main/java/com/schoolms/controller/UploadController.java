package com.schoolms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin
@RestController
@RequestMapping("/upload")
public class UploadController {
	
	
	
	@Autowired
   private HttpServletRequest request;
	@CrossOrigin
	@PostMapping(value="/native",produces = {"text/plain;charset=UTF-8"})
	public String nativeUpload(@RequestParam("file") MultipartFile file){
		
		 //获取上传文件的原始名称
        String originalFilename =  file.getOriginalFilename();
		String path = request.getSession().getServletContext().getRealPath("").toString()+"/upload/";
		String filePath=path+"/"+file.getOriginalFilename();
		File desFile=new File(filePath);
		if(!desFile.getParentFile().exists()){
			desFile.mkdirs();
		}
        //获取当前文件的后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        //使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
        String newFileName = UUID.randomUUID()+ originalFilename+ "." + suffix;
		try {
			file.transferTo(desFile);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "http://localhost:8888/schoolms/upload/"+newFileName;
		//return filePath;
	}
	
		
	@Autowired
	   private HttpServletRequest request1;
		@CrossOrigin
		@PostMapping(value="/nativeshenhe",produces = {"text/plain;charset=UTF-8"})
		public String nativeUploadshenhe(@RequestParam("file") MultipartFile file){
			
			 //获取上传文件的原始名称
	        String originalFilename =  file.getOriginalFilename();
			String path = request1.getSession().getServletContext().getRealPath("").toString()+"/upload/";
			String filePath=path+"/"+file.getOriginalFilename();
			File desFile=new File(filePath);
			if(!desFile.getParentFile().exists()){
				desFile.mkdirs();
			}
	        //获取当前文件的后缀
	        //String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
	        //使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
	        //String newFileName = UUID.randomUUID()+ originalFilename+ "." + suffix;
			try {
				file.transferTo(desFile);
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("__________*************_________________");
			System.out.println(file.getOriginalFilename());
			return "http://localhost:8888/schoolms/upload/"+file.getOriginalFilename();
			//return filePath;
		}
		@Autowired
		   private HttpServletRequest request2;
			@CrossOrigin
			@PostMapping(value="/nativeDown",produces = {"text/plain;charset=UTF-8"})
			public String nativeDown(@RequestParam("file") MultipartFile file){
				
				 //获取上传文件的原始名称
		        String originalFilename =  file.getOriginalFilename();
				String path = request2.getSession().getServletContext().getRealPath("").toString()+"/upload";
				String filePath=path+"/"+file.getOriginalFilename();
				File desFile=new File(filePath);
				if(!desFile.getParentFile().exists()){
					desFile.mkdirs();
				}
		        //获取当前文件的后缀
		      //  String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		        //使用UUID重新命名上传的文件名称(UUID(唯一识别码)+原始文件名称后缀)
		      //  String newFileName = UUID.randomUUID()+ originalFilename+ "." + suffix;
				try {
					file.transferTo(desFile);
			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("__________*************_________________");
				System.out.println(file.getOriginalFilename());
				//return "http://localhost:8888/schoolms/upload/"+file.getOriginalFilename();
				return filePath;
			}
		 /**
	     * <p>Description: 下载</p>
	     *
	     * @param path     路径
	     * @param response
	     */
	    @GetMapping("/api/documents/download")
	    public void download(String path, HttpServletResponse response) {
	        try {
	            // path： 欲下载的文件的路径
	            File file = new File(path);
	            // 获取文件名 - 设置字符集          
	            String downloadFileName = new String(file.getName().getBytes(StandardCharsets.UTF_8), "iso-8859-1");
	            // 以流的形式下载文件
	           System.out.println(downloadFileName+"********************");
	            InputStream fis;
	          
	            fis = new BufferedInputStream(new FileInputStream(path));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();
	            // 清空response
	            response.reset();
	            // 设置response的Header
	            response.addHeader("Content-Disposition", "attachment;filename=" + downloadFileName);
	            response.addHeader("Content-Length", "" + file.length());
	            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	            response.setContentType("application/octet-stream");
	            toClient.write(buffer);
	            toClient.flush();
	            toClient.close();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }

	
}
