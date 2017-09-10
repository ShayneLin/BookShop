package com.bookshop.admin.web;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bookshop.domain.Book;
import com.bookshop.domain.Category;
import com.bookshop.service.BookService;
import com.bookshop.service.CategoryService;
import com.bookshop.utils.BeansHelper;
import com.bookshop.utils.CommonUtils;
import com.bookshop.utils.DateUtil;
import com.bookshop.web.action.BaseAction;

public class AdminAddBookAction extends BaseAction {
	private BookService bookService = (BookService) BeansHelper
			.getBeanService("bookService");//new BookService();
	private String bname;
	private File image_w;
	private String image_wFileName;
	private String image_wContentType;
	private File image_b;
	private String image_bFileName;
	private String image_bContentType;
	private Double currPrice;
	private Double price;
	private Double discount;
	private String author;
	private String press;
	private Date publishtime;
	private int edition;
	private Integer pageNum;
	private Integer wordNum;
	private Date printtime;
	private Integer booksize;
	private String paper;
	private String pid;
	private String cid;
	// 文件类型
	private String fileContentType;

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	private static final long serialVersionUID = 1L;

	public String add() throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		Book book = new Book();

		// 把上传的图片保存起来
		// > 获取文件名：截取之
		// > 给文件添加前缀：使用uuid前缀，为也避免文件同名现象
		// > 校验文件的扩展名：只能是jpg
		// > 校验图片的尺寸
		// > 指定图片的保存路径，这需要使用ServletContext#getRealPath()
		// > 保存之
		// > 把图片的路径设置给Book对象
		System.out.println("image_b:" + image_b);
		System.out.println("image_w:" + image_w);
		String ib_name = getImage_bFileName();
		System.out.println("ib_name:" + ib_name);
		String iw_name = getImage_bFileName();
		System.out.println("iw_name:" + ib_name);
		String filename = writeToDisk(image_b, ib_name);
		if (filename.equals("error")) {
			return error("您上传的图片尺寸超出了350*350！");
		}
		book.setImage_b("book_img/" + filename);
		System.out.println("filename:" + filename);
		filename = writeToDisk(image_w, iw_name);
		if (filename.equals("error")) {
			return error("您上传的图片尺寸超出了350*350！");
		}
		book.setImage_w("book_img/" + filename);
		System.out.println("filename:" + filename);
		// 最终要为Book设置bid应为前台的表单没有
		book.setBid(CommonUtils.uuid());
		// 初始化书本
		book.setBname(bname);
		book.setAuthor(author);
		book.setCurrPrice(currPrice);
		book.setPrice(price);
		book.setCid(cid);
		book.setDiscount(discount);
		book.setEdition(edition);
		book.setPageNum(pageNum);
		book.setPaper(paper);
		book.setPress(press);
		book.setPrinttime(DateUtil.convertDateToFormatString(printtime));
		book.setPublishtime(DateUtil.convertDateToFormatString(publishtime));
		book.setWordNum(wordNum);
		book.setBooksize(booksize);
		// 调用bookService的add方法

		System.out.println("book:" + book);
		bookService.add(book);
		// 设置成功信息
		request.setAttribute("msg", "添加图书成功");
		return "msg";
	}

	private String writeToDisk(File file, String filename) throws IOException {
		// 给文件加上uuid，生成唯一的文件名
		filename = CommonUtils.uuid() + "_" + filename;
		FileInputStream fis = new FileInputStream(file);
		// 先保存图片，才能校验尺寸
		String savePath = request.getServletContext().getRealPath("/book_img");
		System.out.println("savePath:" + savePath);
		// 创建目标文件
		File destfile = new File(savePath, filename);
		// 保存文件
		FileOutputStream fos = new FileOutputStream(destfile);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) != -1) {
			fos.write(buffer, 0, len);
			fos.flush();
		}

		// 校验尺寸
		// 1. 使用文件路径创建ImageIcon
		ImageIcon icon = new ImageIcon(destfile.getAbsolutePath());
		// 2. 通过ImageIcon得到Image对象
		Image image = icon.getImage();
		// 3. 获取宽高来进行校验
		if (image.getWidth(null) > 350 || image.getHeight(null) > 350) {
			destfile.delete();// 删除图片
			return "error";
		}

		return filename;
	}

	// 保存错误信息，转发到add.jsp
	private String error(String msg) {
		request.setAttribute("msg", msg);
		request.setAttribute("parents", new CategoryService().findAllParents());// 所有一级分类
		return "error";
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public File getImage_w() {
		return image_w;
	}

	public void setImage_w(File image_w) {
		this.image_w = image_w;
	}

	public File getImage_b() {
		return image_b;
	}

	public void setImage_b(File image_b) {
		this.image_b = image_b;
	}

	public Double getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(Double currPrice) {
		this.currPrice = currPrice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getWordNum() {
		return wordNum;
	}

	public void setWordNum(Integer wordNum) {
		this.wordNum = wordNum;
	}

	public Date getPrinttime() {
		return printtime;
	}

	public void setPrinttime(Date printtime) {
		this.printtime = printtime;
	}

	public Integer getBooksize() {
		return booksize;
	}

	public void setBooksize(Integer booksize) {
		this.booksize = booksize;
	}

	public String getPaper() {
		return paper;
	}

	public void setPaper(String paper) {
		this.paper = paper;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getImage_wFileName() {
		return image_wFileName;
	}

	public void setImage_wFileName(String image_wFileName) {
		this.image_wFileName = image_wFileName;
	}

	public String getImage_bFileName() {
		return image_bFileName;
	}

	public void setImage_bFileName(String image_bFileName) {
		this.image_bFileName = image_bFileName;
	}

	public String getImage_wContentType() {
		return image_wContentType;
	}

	public void setImage_wContentType(String image_wContentType) {
		this.image_wContentType = image_wContentType;
	}

	public String getImage_bContentType() {
		return image_bContentType;
	}

	public void setImage_bContentType(String image_bContentType) {
		this.image_bContentType = image_bContentType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
