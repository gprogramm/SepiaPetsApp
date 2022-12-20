package com.android.sepiapetsapp.dtos;


import java.util.ArrayList;

public class PetsDto {

    private ArrayList<PetsList> pets;

    public ArrayList<PetsList> getPets() {
        return pets;
    }

    public void setPets(ArrayList<PetsList> pets) {
        this.pets = pets;
    }

    public class PetsList {
        private String image_url;
        private String title;
        private String content_url;
        private String date_added;


        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent_url() {
            return content_url;
        }

        public void setContent_url(String content_url) {
            this.content_url = content_url;
        }

        public String getDate_added() {
            return date_added;
        }

        public void setDate_added(String date_added) {
            this.date_added = date_added;
        }
    }
}
