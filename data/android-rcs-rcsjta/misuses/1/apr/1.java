/*******************************************************************************
 * Software Name : RCS IMS Stack
 *
 * Copyright (C) 2010 France Telecom S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

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
