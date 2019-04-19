package com.demo.domain;

public class Upload {
    private Long id;
    //文件名字
    private String filename;
    //文件路径
    private String filepath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "Upload{" +
                "id=" + id +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }

}