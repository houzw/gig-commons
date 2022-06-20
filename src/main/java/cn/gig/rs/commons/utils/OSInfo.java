package cn.gig.rs.commons.utils;

/**
 * @author houzhiwei
 * @date 2022/1/25 13:49
 */
public class OSInfo {

    private static String OS = System.getProperty("os.name").toLowerCase();

    private OSInfo() {
    }

    public static OsPlatform getOSname() {
        if (OS.contains("linux")) {
            return OsPlatform.Linux;
        }
        if (OS.contains("mac") && OS.indexOf("os") > 0 && !OS.contains("x")) {
            return OsPlatform.Mac_OS;
        }
        if (OS.contains("windows")) {
            return OsPlatform.Windows;
        }
        if (OS.contains("os/2")) {
            return OsPlatform.OS2;
        }
        if (OS.contains("solaris")) {
            return OsPlatform.Solaris;
        }
        if (OS.contains("sunos")) {
            return OsPlatform.SunOS;
        }
        if (OS.contains("mpe/ix")) {
            return OsPlatform.MPEiX;
        }
        if (OS.contains("hp-ux")) {
            return OsPlatform.HP_UX;
        }
        if (OS.contains("aix")) {
            return OsPlatform.AIX;
        }
        if (OS.contains("os/390")) {
            return OsPlatform.OS390;
        }
        if (OS.contains("freebsd")) {
            return OsPlatform.FreeBSD;
        }
        if (OS.contains("irix")) {
            return OsPlatform.Irix;
        }
        if (OS.contains("digital") && OS.contains("unix")) {
            return OsPlatform.Digital_Unix;
        }
        if (OS.contains("netware")) {
            return OsPlatform.NetWare_411;
        }
        if (OS.contains("osf1")) {
            return OsPlatform.OSF1;
        }
        if (OS.contains("openvms")) {
            return OsPlatform.OpenVMS;
        }
        return OsPlatform.Others;
    }
}
