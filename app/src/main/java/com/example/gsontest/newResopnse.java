package com.example.gsontest;

import java.util.List;

/**
 * Created by Harsh on 13-01-2016.
 */
public class newResopnse {


    /**
     * name : Brad Pitt
     * description : William Bradley 'Brad' Pitt is an American actor and film producer. He has received a Golden Globe Award, a Screen Actors Guild Award, and three Academy Award nominations in acting categories
     * dob : December 18, 1963
     * country : United States
     * height : 1.80 m
     * spouse : Jennifer Aniston
     * children : Shiloh Nouvel Jolie-Pitt, Maddox Chivan Jolie-Pitt
     * image : http://microblogging.wingnity.com/JSONParsingTutorial/brad.jpg
     */

    private List<ActorsEntity> actors;

    public void setActors(List<ActorsEntity> actors) {
        this.actors = actors;
    }

    public List<ActorsEntity> getActors() {
        return actors;
    }

    public static class ActorsEntity {
        private String name;
        private String description;
        private String dob;
        private String country;
        private String height;
        private String spouse;
        private String children;
        private String image;

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public void setSpouse(String spouse) {
            this.spouse = spouse;
        }

        public void setChildren(String children) {
            this.children = children;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getDob() {
            return dob;
        }

        public String getCountry() {
            return country;
        }

        public String getHeight() {
            return height;
        }

        public String getSpouse() {
            return spouse;
        }

        public String getChildren() {
            return children;
        }

        public String getImage() {
            return image;
        }
    }
}
