package com.example.demo2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class DataWrapper implements Serializable {
    public String toProductionCompany,movieToTransfer,fromProductionCompany;

    public DataWrapper(String fromProductionCompany,String movieToTransfer, String toProductionCompany){
        this.fromProductionCompany=fromProductionCompany;
        this.toProductionCompany=toProductionCompany;
        this.movieToTransfer=movieToTransfer;
    }
}
