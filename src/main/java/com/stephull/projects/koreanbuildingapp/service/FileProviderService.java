package com.stephull.projects.koreanbuildingapp.service;

import java.io.File;
import java.util.List;

public interface FileProviderService<T> {
    public List<T> processCSVFile(File file);
}
