package com.socialbook.catalogs.errors;

public class InvalidAlbumException extends RuntimeException {
    public InvalidAlbumException(String msg) {
        super(msg);
    }
}
