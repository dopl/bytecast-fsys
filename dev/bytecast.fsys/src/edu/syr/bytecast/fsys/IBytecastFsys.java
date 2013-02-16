package edu.syr.bytecast.fsys;
import java.io.IOException;

public interface IBytecastFsys {
    public void setFilepath(String file_path);
    public String getFilepath();
    public ExeObj parse() throws Exception;
}
