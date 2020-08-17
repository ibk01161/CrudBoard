package net.test.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.test.util.MediaUtils;
import net.test.util.UploadFileUtils;

@Controller
public class Uploadcontroller {
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Uploadcontroller.class);
	
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	// 일반적 파일 업로드 방식
	/*@GetMapping("/uploadForm")
	public void uploadForm() {	
	}
	
	@PostMapping("/uploadForm")
	public String uploadForm(MultipartFile file, Model model) throws Exception {
		logger.info("originalName : " + file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		model.addAttribute("savedName",savedName);
		
		return "uploadResult";
	}*/
	
	/*private String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}*/
	
	// Ajax를 이용한 파일 업로드
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		logger.info("originalName : " + file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());
		
		return new ResponseEntity<>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
	}
	
	// 파일 전송 기능
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("File Neme: " + fileName);
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			org.springframework.http.MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath+fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			} else {
				 fileName = fileName.substring(fileName.indexOf("_")+1);       
			        headers.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);
			        headers.add("Content-Disposition", "attachment; filename=\""+ 
			          new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	// 첨부파일 삭제
	@ResponseBody
	@PostMapping("/deleteAllFiles")
	public ResponseEntity<String> deleteFile(@RequestParam("files[]") String[] files) {
		logger.info("delete all files : " + files);
			
		if (files == null || files.length == 0) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
			
		for (String fileName : files) {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
				
			if (mType == null) {
				String front = fileName.substring(0,12);
				String end = fileName.substring(14);
				new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
			}
			new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	
	

}
