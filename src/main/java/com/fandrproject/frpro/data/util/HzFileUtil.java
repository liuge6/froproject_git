package com.fandrproject.frpro.data.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by sml
 * 2020/09/20 00:09
 */
public class HzFileUtil {
    private static final String separator = "/";

    public HzFileUtil() {
    }

    public static String setContentType(String returnFileName) {
        String contentType = "application/octet-stream";
        if (returnFileName.lastIndexOf(".") < 0) {
            return contentType;
        } else {
            returnFileName = returnFileName.toLowerCase();
            returnFileName = returnFileName.substring(returnFileName.lastIndexOf(".") + 1);
            if (!returnFileName.equals("html") && !returnFileName.equals("htm") && !returnFileName.equals("shtml")) {
                if (returnFileName.equals("apk")) {
                    contentType = "application/vnd.android.package-archive";
                } else if (returnFileName.equals("sis")) {
                    contentType = "application/vnd.symbian.install";
                } else if (returnFileName.equals("sisx")) {
                    contentType = "application/vnd.symbian.install";
                } else if (returnFileName.equals("exe")) {
                    contentType = "application/x-msdownload";
                } else if (returnFileName.equals("msi")) {
                    contentType = "application/x-msdownload";
                } else if (returnFileName.equals("css")) {
                    contentType = "text/css";
                } else if (returnFileName.equals("xml")) {
                    contentType = "text/xml";
                } else if (returnFileName.equals("gif")) {
                    contentType = "image/gif";
                } else if (!returnFileName.equals("jpeg") && !returnFileName.equals("jpg")) {
                    if (returnFileName.equals("js")) {
                        contentType = "application/x-javascript";
                    } else if (returnFileName.equals("atom")) {
                        contentType = "application/atom+xml";
                    } else if (returnFileName.equals("rss")) {
                        contentType = "application/rss+xml";
                    } else if (returnFileName.equals("mml")) {
                        contentType = "text/mathml";
                    } else if (returnFileName.equals("txt")) {
                        contentType = "text/plain";
                    } else if (returnFileName.equals("jad")) {
                        contentType = "text/vnd.sun.j2me.app-descriptor";
                    } else if (returnFileName.equals("wml")) {
                        contentType = "text/vnd.wap.wml";
                    } else if (returnFileName.equals("htc")) {
                        contentType = "text/x-component";
                    } else if (returnFileName.equals("png")) {
                        contentType = "image/png";
                    } else if (!returnFileName.equals("tif") && !returnFileName.equals("tiff")) {
                        if (returnFileName.equals("wbmp")) {
                            contentType = "image/vnd.wap.wbmp";
                        } else if (returnFileName.equals("ico")) {
                            contentType = "image/x-icon";
                        } else if (returnFileName.equals("jng")) {
                            contentType = "image/x-jng";
                        } else if (returnFileName.equals("bmp")) {
                            contentType = "image/x-ms-bmp";
                        } else if (returnFileName.equals("svg")) {
                            contentType = "image/svg+xml";
                        } else if (!returnFileName.equals("jar") && !returnFileName.equals("var") && !returnFileName.equals("ear")) {
                            if (returnFileName.equals("doc")) {
                                contentType = "application/msword";
                            } else if (returnFileName.equals("pdf")) {
                                contentType = "application/pdf";
                            } else if (returnFileName.equals("rtf")) {
                                contentType = "application/rtf";
                            } else if (returnFileName.equals("xls")) {
                                contentType = "application/vnd.ms-excel";
                            } else if (returnFileName.equals("ppt")) {
                                contentType = "application/vnd.ms-powerpoint";
                            } else if (returnFileName.equals("7z")) {
                                contentType = "application/x-7z-compressed";
                            } else if (returnFileName.equals("rar")) {
                                contentType = "application/x-rar-compressed";
                            } else if (returnFileName.equals("swf")) {
                                contentType = "application/x-shockwave-flash";
                            } else if (returnFileName.equals("rpm")) {
                                contentType = "application/x-redhat-package-manager";
                            } else if (!returnFileName.equals("der") && !returnFileName.equals("pem") && !returnFileName.equals("crt")) {
                                if (returnFileName.equals("xhtml")) {
                                    contentType = "application/xhtml+xml";
                                } else if (returnFileName.equals("zip")) {
                                    contentType = "application/zip";
                                } else if (!returnFileName.equals("mid") && !returnFileName.equals("midi") && !returnFileName.equals("kar")) {
                                    if (returnFileName.equals("mp3")) {
                                        contentType = "audio/mpeg";
                                    } else if (returnFileName.equals("ogg")) {
                                        contentType = "audio/ogg";
                                    } else if (returnFileName.equals("m4a")) {
                                        contentType = "audio/x-m4a";
                                    } else if (returnFileName.equals("ra")) {
                                        contentType = "audio/x-realaudio";
                                    } else if (!returnFileName.equals("3gpp") && !returnFileName.equals("3gp")) {
                                        if (returnFileName.equals("mp4")) {
                                            contentType = "video/mp4";
                                        } else if (!returnFileName.equals("mpeg") && !returnFileName.equals("mpg")) {
                                            if (returnFileName.equals("mov")) {
                                                contentType = "video/quicktime";
                                            } else if (returnFileName.equals("flv")) {
                                                contentType = "video/x-flv";
                                            } else if (returnFileName.equals("m4v")) {
                                                contentType = "video/x-m4v";
                                            } else if (returnFileName.equals("mng")) {
                                                contentType = "video/x-mng";
                                            } else if (!returnFileName.equals("asx") && !returnFileName.equals("asf")) {
                                                if (returnFileName.equals("wmv")) {
                                                    contentType = "video/x-ms-wmv";
                                                } else if (returnFileName.equals("avi")) {
                                                    contentType = "video/x-msvideo";
                                                }
                                            } else {
                                                contentType = "video/x-ms-asf";
                                            }
                                        } else {
                                            contentType = "video/mpeg";
                                        }
                                    } else {
                                        contentType = "video/3gpp";
                                    }
                                } else {
                                    contentType = "audio/midi";
                                }
                            } else {
                                contentType = "application/x-x509-ca-cert";
                            }
                        } else {
                            contentType = "application/java-archive";
                        }
                    } else {
                        contentType = "image/tiff";
                    }
                } else {
                    contentType = "image/jpeg";
                }
            } else {
                contentType = "text/html";
            }

            return contentType;
        }
    }
}
