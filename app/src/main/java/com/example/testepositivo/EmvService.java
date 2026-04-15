package com.example.testepositivo;

import br.com.positivo.emv.lib.EmvLib;

public class EmvService {

    public String getTagSync(String tag) {
        return EmvLib.INSTANCE.getEmvTagInHexStringSync(tag);
    }

    public void getTagAsync(String tag, Callback callback) {
        EmvLib.INSTANCE.getEmvTagInHexStringAsync(tag, result -> {
            callback.onResult(result);
            return null;
        });
    }

    public interface Callback {
        void onResult(String result);
    }
}