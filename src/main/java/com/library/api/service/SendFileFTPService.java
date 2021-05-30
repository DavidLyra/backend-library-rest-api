package com.library.api.service;

import java.io.InputStream;

public interface SendFileFTPService {
     void sendFileFtp(InputStream file, String fileName, String hostDir) throws Exception;
}
