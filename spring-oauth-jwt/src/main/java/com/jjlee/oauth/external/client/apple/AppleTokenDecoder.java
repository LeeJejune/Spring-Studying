package com.jjlee.oauth.external.client.apple;


import javax.validation.constraints.NotNull;

public interface AppleTokenDecoder {
    String getSocialIdFromIdToken(@NotNull String idToken);
}
