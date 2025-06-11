package com.orangelabs.rcs.core.ims.service.im.chat;

import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import com.orangelabs.rcs.platform.AndroidFactory;
import com.orangelabs.rcs.utils.DeviceUtils;

public class ContributionIdGenerator {
    private static byte[] secretKey = generateSecretKey();

    private static byte[] generateSecretKey() {
        UUID uuid = DeviceUtils.getDeviceUUID(AndroidFactory.getApplicationContext());
        byte[] key;
        if (uuid != null) {
            key = uuid.toString().getBytes();
        } else {
            String t = "" + System.currentTimeMillis();
            key = t.getBytes();
        }
        byte[] secretKey = new byte[16];
        for (int i = 0; i < 16; i++) {
            if (key != null && key.length >= 16) {
                secretKey[i] = key[i];
            } else {
                secretKey[i] = '0';
            }
        }
        return secretKey;
    }

    public synchronized static String getContributionId(String callId) {
        try {
            SecretKeySpec sks = new SecretKeySpec(secretKey, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(sks);
            byte[] contributionId = mac.doFinal(callId.getBytes());
            StringBuilder hexString = new StringBuilder(32);
            for (int i = 0; i < 16 && i < contributionId.length; i++) {
                String hex = Integer.toHexString(0xFF & contributionId[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            String id = hexString.toString();
            return id;
        } catch(Exception e) {
            return null;
        }
    }
}
