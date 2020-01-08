package cn.itcast.practice.decrypt;

import com.chilkatsoft.*;

public class chilkat {

    static {
        try {
            System.out.println(System.getProperty("java.library.path"));
            System.loadLibrary("chilkat");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[])
    {
        // The Chilkat API can be unlocked for a fully-functional 30-day trial by passing any
        // string to the UnlockBundle method.  A program can unlock once at the start. Once unlocked,
        // all subsequently instantiated objects are created in the unlocked state.
        //
        // After licensing Chilkat, replace the "Anything for 30-day trial" with the purchased unlock code.
        // To verify the purchased unlock code was recognized, examine the contents of the LastErrorText
        // property after unlocking.  For example:
        CkGlobal glob = new CkGlobal();
        boolean success = glob.UnlockBundle("Anything for 30-day trial");
        if (success != true) {
            System.out.println(glob.lastErrorText());
            return;
        }

        int status = glob.get_UnlockStatus();
        if (status == 2) {
            System.out.println("Unlocked using purchased unlock code.");
        }
        else {
            System.out.println("Unlocked in trial mode.");
        }

        // The LastErrorText can be examined in the success case to see if it was unlocked in
        // trial more, or with a purchased unlock code.
        System.out.println(glob.lastErrorText());
    }
}