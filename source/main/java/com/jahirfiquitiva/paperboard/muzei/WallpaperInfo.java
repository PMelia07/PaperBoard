package com.jahirfiquitiva.paperboard.muzei;

public class WallpaperInfo {

    String wall_name, wall_author, wall_url;

    public WallpaperInfo(String wall_name, String wall_author, String wall_url) {
        this.wall_name = wall_name;
        this.wall_author = wall_author;
        this.wall_url = wall_url;
    }

    public String getWallName() {
        return wall_name;
    }

    public String getWallAuthor() {
        return wall_author;
    }

    public String getWallURL() {
        return wall_url;
    }

}
