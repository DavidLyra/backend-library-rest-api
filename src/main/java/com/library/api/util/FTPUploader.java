package com.library.api.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

@Slf4j
public class FTPUploader {

    FTPClient ftp;

    public FTPUploader(String host, String user, String pwd) throws IOException {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            log.error("Exception in connecting to FTP Server");
        }
        ftp.login(user, pwd);
        ftp.enterLocalPassiveMode();
    }

    public void uploadFile(InputStream file, String fileName, String hostDir) throws Exception {
            this.ftp.storeFile(hostDir + fileName, file);
    }

    public void disconnect(){
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already saved to server
            }
        }
    }
}
