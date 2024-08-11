package com.example.finalapplication;

public class Students {

    public String Name,addres,prov,contac,imageId;
    public int id;

    public Students () {

    }

    public  Students(String name,String ad, String pro, String cont,String image,int uniq_id)
    {
        this.id = uniq_id;
        this.Name=name;
        this.addres=ad;
        this.prov=pro;
        this.contac=cont;
        this.imageId = image;


    }


}
