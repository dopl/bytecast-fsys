package edu.syr.bytecast.fsys;
import java.io.IOException;

public interface ExeObjFileParser {
    public void setFilepath();
    public String getFilepath();
    ExeObj parse() throws IOException;
}
