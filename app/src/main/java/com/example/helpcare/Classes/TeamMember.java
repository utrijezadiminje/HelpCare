package com.example.helpcare.Classes;

import android.media.Image;
import android.widget.ImageView;

public class TeamMember {

     private String name;
     private String number;
     private Image image;

     public TeamMember(String name,String number,Image image)
     {
         this.name=name;
         this.number=number;
         this.image=image;
     }

    public String getMemberName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public String getNumber() {
        return number;
    }
}
