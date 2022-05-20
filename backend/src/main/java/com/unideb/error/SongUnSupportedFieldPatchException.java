package com.unideb.error;

import java.util.Set;

public class SongUnSupportedFieldPatchException extends RuntimeException
{
    public SongUnSupportedFieldPatchException(Set<String> keys)
    {
        super("Field " + keys.toString() + " update is not allowed!");
    }
}
