package test.agent.helpers;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.Kernel32Util;

/**
 * MIT License
 * Copyright (c) 2017 Matt Sheppard
 */
public class Hostname {
    /**
     * @return the hostname the of the current machine
     */
    public static String getHostname() {
        if (Platform.isWindows()) {
            return Kernel32Util.getComputerName();
        } else {
            // For now, we'll consider anyhting other than Windows to be unix-ish enough to have gethostname
            // TODO - Consider http://stackoverflow.com/a/10543006 as a possibly better MacOS option

            byte[] hostnameBuffer = new byte[4097];
            // http://pubs.opengroup.org/onlinepubs/9699919799/basedefs/limits.h.html suggests
            // the actual limit would be 255.

            int result = UnixCLibrary.INSTANCE.gethostname(hostnameBuffer, hostnameBuffer.length);
            if (result != 0) {
                throw new RuntimeException("gethostname call failed");
            }

            return Native.toString(hostnameBuffer);
        }
    }

    private interface UnixCLibrary extends Library {
        UnixCLibrary INSTANCE = (UnixCLibrary) Native.load("c", UnixCLibrary.class);

        int gethostname(byte[] hostname, int bufferSize);
    }
}
