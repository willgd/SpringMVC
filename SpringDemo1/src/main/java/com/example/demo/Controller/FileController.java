package com.example.demo.Controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FileController {
    @RequestMapping("/testDownloadPicture")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取servletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取文件真实路径
        String realPath = servletContext.getRealPath("/img/IMG_0003.jpg");
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数字
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        //创建httpHeaders对象设置响应头信息
        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, status);
        is.close();
        return responseEntity;
    }

    @RequestMapping(value = "/testDownload",method = RequestMethod.POST)
    public void testResponseEntity(HttpServletResponse response,HttpSession session) throws IOException {
        //获取servletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取文件真实路径
        String realPath = servletContext.getRealPath("/img/MP4_003.MP4");
        File file = new File(realPath);
        //创建输入流
        response.reset();
        response.setContentType("application/octet-stream");
        String name = new SimpleDateFormat("yyyyMMdd_ss").format(new Date()) + realPath.substring(realPath.lastIndexOf("."));
        System.out.println(name);
        response.setHeader("Content-Disposition", "attachment;filename=" + name);

        response.setHeader("Content-Length", file.length() + "");

        InputStream bis = new BufferedInputStream(new FileInputStream(file));

        OutputStream bos = new BufferedOutputStream(response.getOutputStream());
        //创建字节数字
        byte[] body = new byte[1024];

        int i = -1;

        while ((i = bis.read(body)) != -1){
            bos.write(body, 0, i);

        }
        bis.close();

        bos.flush();

        bos.close();

    }

    @RequestMapping("/testOnload")
    public String testOnload(MultipartFile picture,HttpSession session) throws IOException {
        String filename = picture.getOriginalFilename();
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("picture");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdir();
        }
        String Path = realPath + File.separator + filename;
        picture.transferTo(new File(Path));
        return "success";
    }
}
