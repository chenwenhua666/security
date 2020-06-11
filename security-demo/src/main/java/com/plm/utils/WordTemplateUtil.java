package com.plm.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author : cwh
 * 2018/11/25 0025
 * description ：
 */
public class WordTemplateUtil {

    /**
     * 试题模板下载
     *
     * @param response
     */
    public static void downloadTemplate(HttpServletResponse response) {
        String fileName = "word_template.docx";
        InputStream stream = WordTemplateUtil.class.getClassLoader().getResourceAsStream("static/doc/" + fileName);
        response.setContentType("application/x-msdownload;");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(stream);
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
        } catch (Exception e) {
            // throw new ExamQuestionException(ResultEnum.DOWNLOAD_FAILED.getCode(), e.getMessage());
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                //  throw new ExamQuestionException(ResultEnum.DOWNLOAD_FAILED.getCode(), e.getMessage());
            }

        }
    }

    /**
     * 上传word模板
     *
     * @param file
     */
    public static void uploadWordTemplate(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                // throw new ExamQuestionException(ResultEnum.UPLOAD_FAILED);
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (!(suffixName.equals("doc") || suffixName.equals("docx") || suffixName.equals("rtf"))) {
                //  throw new ExamQuestionException(ResultEnum.UPLOAD_FAILED);
            }
            // 设置文件存储路径
            String filePath = "C://Users//Administrator//Downloads//";
            String path = filePath + fileName;

            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
        } catch (IOException e) {
            // throw new ExamQuestionException(ResultEnum.UPLOAD_FAILED);
        }
    }


}
