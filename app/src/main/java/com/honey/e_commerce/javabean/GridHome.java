package com.honey.e_commerce.javabean;

/**
 * 1.类的用途
 * 2.@authorAdministrator
 */

public class GridHome {
    private String home_title;
    private int homeimage;

    public GridHome(String home_title, int homeimage) {
        this.home_title = home_title;
        this.homeimage = homeimage;
    }

    public String getHome_title() {
        return home_title;
    }

    public void setHome_title(String home_title) {
        this.home_title = home_title;
    }

    public int getHomeimage() {
        return homeimage;
    }

    public void setHomeimage(int homeimage) {
        this.homeimage = homeimage;
    }

    public GridHome() {
        super();
    }

    @Override
    public String toString() {
        return "GridHome{" +
                "home_title='" + home_title + '\'' +
                ", homeimage=" + homeimage +
                '}';
    }
}
