package com.schoolms.model;
/*
 * 文件类，对应数据库文件file_Info
 * file_id
 * file_name
 * file_url
 * */
public class FileInfo {
	private String fileId;
	private String fileName;
	private String fileUrl;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
}