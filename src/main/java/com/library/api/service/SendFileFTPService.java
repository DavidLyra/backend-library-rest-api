package com.library.api.service;

import com.library.api.util.FTPUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Slf4j
@Service
public class SendFileFTPService {

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.server}")
    private String server;

    public void sendFileFtp(InputStream file, String fileName, String hostDir) throws Exception {
        FTPUploader ftpUploader = new FTPUploader(server, username, password);
        ftpUploader.uploadFile(file, fileName, hostDir);
        ftpUploader.disconnect();
        log.info("File sent to FTP Server!");
    }
}
